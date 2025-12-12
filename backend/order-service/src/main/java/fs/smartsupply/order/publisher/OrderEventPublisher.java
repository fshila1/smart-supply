package fs.smartsupply.order.publisher;

import fs.smartsupply.common.events.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreated(OrderCreatedEvent event) {
        publish("order.created", event);
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
