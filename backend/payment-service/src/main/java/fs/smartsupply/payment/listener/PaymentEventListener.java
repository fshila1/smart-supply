package fs.smartsupply.payment.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.PaymentInitiatedEvent;
import fs.smartsupply.payment.service.PaymentWorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final PaymentWorkflowService workflowService;

    @KafkaListener(topics = KafkaTopicConfig.PAYMENT_INITIATED, groupId = "payment-service")
    public void handlePaymentInitiated(PaymentInitiatedEvent event) {
        log.info("Received payment initiated event: {}", event);
        workflowService.processPayment(event);
    }
}
