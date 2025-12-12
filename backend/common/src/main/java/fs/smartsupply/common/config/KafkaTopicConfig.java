package fs.smartsupply.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class KafkaTopicConfig {

    // ---------------------------
    // ORDER EVENTS
    // ---------------------------
    public static final String ORDER_CREATED = "order.created";
    public static final String ORDER_CONFIRMED = "order.confirmed";
    public static final String ORDER_CANCELLED = "order.cancelled";

    // ---------------------------
    // INVENTORY EVENTS
    // ---------------------------
    public static final String STOCK_RESERVE_REQUEST = "stock.reserve.request";
    public static final String STOCK_RESERVED = "stock.reserved";
    public static final String STOCK_RESERVATION_FAILED = "stock.reservation.failed";
    public static final String STOCK_RELEASED = "stock.released";

    // ---------------------------
    // PAYMENT EVENTS
    // ---------------------------
    public static final String PAYMENT_INITIATED = "payment.initiated";
    public static final String PAYMENT_COMPLETED = "payment.completed";
    public static final String PAYMENT_FAILED = "payment.failed";

    // ---------------------------
    // NOTIFICATION EVENTS
    // ---------------------------
    public static final String NOTIFY_ORDER_STATUS = "notification.order.status";
    public static final String NOTIFY_PAYMENT_STATUS = "notification.payment.status";

    // ---------------------------
    // ANALYTICS EVENTS
    // ---------------------------
    public static final String ANALYTICS_ORDER_EVENT = "analytics.order.event";
    public static final String ANALYTICS_PAYMENT_EVENT = "analytics.payment.event";

    // ---------------------------
    // CONTROL AUTO-CREATION
    // ---------------------------
    @Value("${kafka.create-topics:true}")
    private boolean createTopicsEnabled;

    // ---------------------------
    // Conditional Topic Creation
    // ---------------------------
    @Bean
    public KafkaAdmin.NewTopics createAllTopics() {
        if (!createTopicsEnabled) {
            return new KafkaAdmin.NewTopics();
        }

        return new KafkaAdmin.NewTopics(
            TopicBuilder.name(ORDER_CREATED).partitions(3).replicas(1).build(),
            TopicBuilder.name(ORDER_CONFIRMED).partitions(3).replicas(1).build(),
            TopicBuilder.name(ORDER_CANCELLED).partitions(3).replicas(1).build(),

            TopicBuilder.name(STOCK_RESERVE_REQUEST).partitions(3).replicas(1).build(),
            TopicBuilder.name(STOCK_RESERVED).partitions(3).replicas(1).build(),
            TopicBuilder.name(STOCK_RESERVATION_FAILED).partitions(3).replicas(1).build(),
            TopicBuilder.name(STOCK_RELEASED).partitions(3).replicas(1).build(),

            TopicBuilder.name(PAYMENT_INITIATED).partitions(3).replicas(1).build(),
            TopicBuilder.name(PAYMENT_COMPLETED).partitions(3).replicas(1).build(),
            TopicBuilder.name(PAYMENT_FAILED).partitions(3).replicas(1).build(),

            TopicBuilder.name(NOTIFY_ORDER_STATUS).partitions(2).replicas(1).build(),
            TopicBuilder.name(NOTIFY_PAYMENT_STATUS).partitions(2).replicas(1).build(),

            TopicBuilder.name(ANALYTICS_ORDER_EVENT).partitions(3).replicas(1).build(),
            TopicBuilder.name(ANALYTICS_PAYMENT_EVENT).partitions(3).replicas(1).build()
        );
    }
}
