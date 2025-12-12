package fs.smartsupply.order.service;

import fs.smartsupply.common.events.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderWorkflowService {

    public void onStockReserved(ProductStockReservedEvent event) {
        log.info("Order {} stock reserved → updating order status", event.getOrderId());
        // update order → RESERVED
        // initiate payment event
    }

    public void onStockReservationFailed(ProductStockReservationFailedEvent event) {
        log.info("Order {} stock reservation FAILED → cancel order", event.getOrderId());
        // update order → CANCELLED
    }

    public void onPaymentCompleted(PaymentCompletedEvent event) {
        log.info("Order {} payment completed", event.getOrderId());
        // update order → CONFIRMED
    }

    public void onPaymentFailed(PaymentFailedEvent event) {
        log.info("Order {} payment failed", event.getOrderId());
        // update order → CANCELLED + release stock
    }
}
