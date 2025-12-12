package fs.smartsupply.analytics.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.OrderCreatedEvent;
import fs.smartsupply.common.events.PaymentCompletedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AnalyticsEventListener {

    @KafkaListener(topics = KafkaTopicConfig.ANALYTICS_ORDER_EVENT, groupId = "analytics-service")
    public void handleOrderEvent(OrderCreatedEvent event) {
        log.info("Analytics received order event: {}", event);
        // save to warehouse / bigquery / etc
    }

    @KafkaListener(topics = KafkaTopicConfig.ANALYTICS_PAYMENT_EVENT, groupId = "analytics-service")
    public void handlePaymentEvent(PaymentCompletedEvent event) {
        log.info("Analytics received payment event: {}", event);
    }
}
