package fs.smartsupply.payment.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.PaymentCompletedEvent;
import fs.smartsupply.common.events.PaymentFailedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.PAYMENT_COMPLETED, event);
    }

    public void publishPaymentFailed(PaymentFailedEvent event) {
        kafkaTemplate.send(KafkaTopicConfig.PAYMENT_FAILED, event);
    }
}
