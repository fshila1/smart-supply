package fs.smartsupply.order.listener;

import fs.smartsupply.common.events.*;
import fs.smartsupply.order.service.OrderWorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderWorkflowService orderWorkflowService;

    @KafkaListener(topics = "stock.reserved", groupId = "order-service")
    public void handleStockReserved(ProductStockReservedEvent event) {
        log.info("Received ProductStockReservedEvent: {}", event);
        orderWorkflowService.onStockReserved(event);
    }

    @KafkaListener(topics = "stock.reservation.failed", groupId = "order-service")
    public void handleStockReservationFailed(ProductStockReservationFailedEvent event) {
        log.info("Received ProductStockReservationFailedEvent: {}", event);
        orderWorkflowService.onStockReservationFailed(event);
    }

    @KafkaListener(topics = "payment.completed", groupId = "order-service")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Received PaymentCompletedEvent: {}", event);
        orderWorkflowService.onPaymentCompleted(event);
    }

    @KafkaListener(topics = "payment.failed", groupId = "order-service")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.info("Received PaymentFailedEvent: {}", event);
        orderWorkflowService.onPaymentFailed(event);
    }
}
