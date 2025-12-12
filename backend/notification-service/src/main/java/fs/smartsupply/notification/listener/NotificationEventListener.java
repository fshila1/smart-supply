package fs.smartsupply.notification.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import fs.smartsupply.common.config.KafkaTopicConfig;
import fs.smartsupply.common.events.OrderStatusNotificationEvent;
import fs.smartsupply.common.events.PaymentStatusNotificationEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationEventListener {

    @KafkaListener(topics = KafkaTopicConfig.NOTIFY_ORDER_STATUS, groupId = "notification-service")
    public void handleOrderStatusNotification(OrderStatusNotificationEvent event) {
        // send email/sms
    }

    @KafkaListener(topics = KafkaTopicConfig.NOTIFY_PAYMENT_STATUS, groupId = "notification-service")
    public void handlePaymentNotification(PaymentStatusNotificationEvent event) {
        // send email/sms
    }
}
