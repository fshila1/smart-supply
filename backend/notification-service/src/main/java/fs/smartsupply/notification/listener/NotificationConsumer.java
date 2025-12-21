package fs.smartsupply.notification.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.model.NotificationEvent;
import fs.smartsupply.notification.service.EmailService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(
        topics = "notification-events",
        groupId = "notification-service"
    )
    public void consume(NotificationEvent event) {
        switch (event.getEventType()) {
            case "ORDER_PLACED" -> emailService.sendOrderPlacedEmail(event);
            // case "SUPPLIER_REGISTERED" -> emailService.sendSupplierWelcome(event);
            default -> System.out.println("default");
        }
    }
}
