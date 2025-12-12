package fs.smartsupply.inventory.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.OrderCreatedEvent;
import fs.smartsupply.inventory.service.InventoryWorkflowService;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryEventListener {

    private final InventoryWorkflowService workflowService;

    @KafkaListener(topics = KafkaTopicConfig.STOCK_RESERVE_REQUEST, groupId = "inventory-service")
    public void handleStockReserveRequest(OrderCreatedEvent event) {
        log.info("Received StockReserveRequest for order {}", event.getOrderId());
        workflowService.onReserveStock(event);
    }
}
