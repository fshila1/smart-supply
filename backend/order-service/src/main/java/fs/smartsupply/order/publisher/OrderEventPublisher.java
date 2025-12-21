package fs.smartsupply.order.publisher;

import fs.smartsupply.common.events.*;
import fs.smartsupply.common.model.NotificationEvent;
import fs.smartsupply.common.model.NotificationEvent.Recipient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    // private final KafkaTemplate<String, NotificationEvent> kafkaNotificationTemplate;

    public void publishOrderCreated(OrderCreatedEvent event) {
        publish("order.created", event);
        Recipient recipient = Recipient.builder()
            .email("fas.shila@gmail.com")
            .name("shila")
            .build();
        kafkaTemplate.send(
            "notification-events",
            NotificationEvent.builder()
            .eventId(UUID.randomUUID().toString())
            .eventType("ORDER_PLACED")
            .recipient(recipient)
            .data(Map.of("orderId", event.getOrderId(), "amount", event.getTotalAmount()))
            .timestamp(Instant.now())
            .build()
        );
    }

    public void publishOrderConfirmed(OrderConfirmedEvent event) {
        publish("order.confirmed", event);
    }

    public void publishOrderCancelled(OrderCancelledEvent event) {
        publish("order.cancelled", event);
    }

    public void publishStockReservationRequested(Long orderId) {
        log.info("Publishing stock reservation request for order {}", orderId);
        kafkaTemplate.send("stock.reserve.request", orderId);
    }

    public void publishPaymentInitiated(PaymentInitiatedEvent event) {
        publish("payment.initiated", event);
    }

    private void publish(String topic, Object event) {
        log.info("Publishing event to topic {} -> {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
}
