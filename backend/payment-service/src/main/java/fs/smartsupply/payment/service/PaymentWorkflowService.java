package fs.smartsupply.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import fs.smartsupply.common.events.PaymentCompletedEvent;
import fs.smartsupply.common.events.PaymentFailedEvent;
import fs.smartsupply.common.events.PaymentInitiatedEvent;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentWorkflowService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Kafka topics are imported from KafkaTopicConfig
    private static final String PAYMENT_COMPLETED_TOPIC = "payment.completed";
    private static final String PAYMENT_FAILED_TOPIC = "payment.failed";

    // ============================
    // 1️⃣ Process Payment
    // ============================
    public void processPayment(PaymentInitiatedEvent event) { 

        log.info("Processing PaymentInitiatedEvent: {}", event);

        try {
            // Simulate payment processing
            boolean success = simulatePaymentGateway(event);

            if (success) {
                publishPaymentCompleted(event);
            } else {
                publishPaymentFailed(event, "Payment declined by gateway");
            }

        } catch (Exception e) {
            log.error("Payment processing failed for order {}: {}", event.getOrderId(), e.getMessage());
            publishPaymentFailed(event, e.getMessage());
        }
    }

    // ============================
    // 2️⃣ Simulated Payment Gateway
    // ============================
    private boolean simulatePaymentGateway(PaymentInitiatedEvent event) {
        // For demo/testing purposes, randomly succeed/fail
        Random random = new Random();
        return random.nextBoolean();
    }

    // ============================
    // 3️⃣ Publish PaymentCompletedEvent
    // ============================
    private void publishPaymentCompleted(PaymentInitiatedEvent event) {

        PaymentCompletedEvent completedEvent = new PaymentCompletedEvent();
        completedEvent.setOrderId(event.getOrderId());
        completedEvent.setTransactionId(generateTransactionId());
        completedEvent.setAmount(event.getAmount());
        completedEvent.setCurrency(event.getCurrency());

        kafkaTemplate.send(PAYMENT_COMPLETED_TOPIC, completedEvent);
        log.info("Published PaymentCompletedEvent: {}", completedEvent);
    }

    // ============================
    // 4️⃣ Publish PaymentFailedEvent
    // ============================
    private void publishPaymentFailed(PaymentInitiatedEvent event, String reason) {

        PaymentFailedEvent failedEvent = new PaymentFailedEvent();
        failedEvent.setOrderId(event.getOrderId());
        failedEvent.setReason(reason);

        kafkaTemplate.send(PAYMENT_FAILED_TOPIC, failedEvent);
        log.warn("Published PaymentFailedEvent: {}", failedEvent);
    }

    // ============================
    // 5️⃣ Transaction ID Generator
    // ============================
    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
    }
}
