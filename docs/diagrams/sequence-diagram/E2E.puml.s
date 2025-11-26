@startuml
title End-to-End Order Creation Flow

actor User

participant "OrderService" as OS
participant "InventoryService" as IS
participant "PaymentService" as PS
participant "NotificationService" as NS
participant "AnalyticsService" as AS
participant "Payment Gateway" as PG
collections Kafka

== User Places Order ==
User -> OS: CreateOrderRequest(items, userInfo)
OS -> OS: Validate request
OS -> OS: Create Order(status=CREATED)
OS -> Kafka: OrderCreatedEvent

AS <- Kafka: OrderCreatedEvent
AS -> AS: Log order creation

== Inventory Reservation ==
Kafka -> IS: OrderCreatedEvent
IS -> IS: Check stock availability

alt Any item unavailable
    IS -> Kafka: StockNotAvailableEvent
    AS <- Kafka: StockNotAvailableEvent
    AS -> AS: Log stock failure
    OS <- Kafka: StockNotAvailableEvent
    OS -> OS: Update Order(status=CANCELLED_OUT_OF_STOCK)
    OS -> Kafka: OrderCancelledEvent
    NS <- Kafka: OrderCancelledEvent
    NS -> NS: Send out-of-stock notification
    return
else All items available
    IS -> IS: Reserve stock (TTL applied)
    IS -> Kafka: StockReservedEvent
end

AS <- Kafka: StockReservedEvent
AS -> AS: Log stock reservation

OS <- Kafka: StockReservedEvent
OS -> OS: Update Order(status=RESERVED)
OS -> Kafka: PaymentInitiationRequestedEvent

== Payment Processing ==
Kafka -> PS: PaymentInitiationRequestedEvent
PS -> PS: Save payment(status=INITIATED)
PS -> PS: Validate payment info

alt Payment validation failed
    PS -> PS: Update payment(status=INVALID)
    PS -> Kafka: PaymentFailureEvent(reason=VALIDATION_FAILED)
else Validation ok
    PS -> PS: Update payment(status=PROCESSING)
    PS -> PG: Request payment
    PG --> PS: PaymentResult(SUCCESS/FAILED/PENDING/TIMEOUT/ERROR)
    PS -> PS: Update payment(status=RESULT)
    PS -> Kafka: PaymentSuccessEvent OR PaymentFailureEvent OR PaymentPendingEvent
end

AS <- Kafka: PaymentSuccessEvent or PaymentFailureEvent or PaymentPendingEvent
AS -> AS: Log payment result

OS <- Kafka: PaymentSuccessEvent or PaymentFailureEvent

== Order Decision After Payment ==

alt Payment SUCCESS
    OS -> OS: Update Order(status=PAYMENT_CONFIRMED)
    OS -> Kafka: OrderConfirmedEvent
else Payment FAILED
    OS -> OS: Update Order(status=PAYMENT_FAILED)
    OS -> Kafka: OrderCancelledEvent
else Payment PENDING
    OS -> OS: Update Order(status=PAYMENT_PENDING)
end

NS <- Kafka: PaymentSuccessEvent / PaymentFailureEvent
NS -> NS: Send payment notification

== Inventory Finalization ==
Kafka -> IS: OrderConfirmedEvent OR OrderCancelledEvent

alt OrderConfirmedEvent
    IS -> IS: Deduct reserved stock permanently
    IS -> Kafka: StockDeductedEvent
else OrderCancelledEvent
    IS -> IS: Release reserved stock
    IS -> Kafka: StockReleasedEvent
end

AS <- Kafka: StockDeductedEvent / StockReleasedEvent
AS -> AS: Log final stock update

== Reservation TTL Expiry ==
group Background Job: Reservation TTL
    IS -> IS: Check expired reservations
    IS -> Kafka: StockReservationExpiredEvent
    OS <- Kafka: StockReservationExpiredEvent
    OS -> OS: Update Order(status=CANCELLED_RESERVATION_EXPIRED)
    NS <- Kafka: OrderCancelledEvent
    NS -> NS: Notify user reservation expired
    AS <- Kafka: StockReservationExpiredEvent
    AS -> AS: Log TTL expiry
end

== Final Notification ==
NS <- Kafka: OrderConfirmedEvent / OrderCancelledEvent
NS -> NS: Send final order notification to user

@enduml
