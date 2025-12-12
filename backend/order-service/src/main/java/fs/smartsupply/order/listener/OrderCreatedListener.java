package fs.smartsupply.order.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import fs.smartsupply.common.events.OrderCreatedEvent;

@Service
public class OrderCreatedListener {

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void handle(OrderCreatedEvent event) {
        // reduce or reserve stock
    }
}
