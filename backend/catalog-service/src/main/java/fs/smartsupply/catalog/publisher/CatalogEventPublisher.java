package fs.smartsupply.catalog.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.ProductUpdatedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishProductUpdated(ProductUpdatedEvent event) {
        log.info("Publishing ProductUpdatedEvent: {}", event);
        kafkaTemplate.send(KafkaTopicConfig.ANALYTICS_ORDER_EVENT, event);
    }
}
