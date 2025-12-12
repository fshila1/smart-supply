package fs.smartsupply.inventory.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.ProductStockReleasedEvent;
import fs.smartsupply.common.events.ProductStockReservationFailedEvent;
import fs.smartsupply.common.events.ProductStockReservedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishStockReserved(ProductStockReservedEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.STOCK_RESERVED, event);
    }

    public void publishStockReservationFailed(ProductStockReservationFailedEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.STOCK_RESERVATION_FAILED, event);
    }

    public void publishStockReleased(ProductStockReleasedEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.STOCK_RELEASED, event);
    }
}
