package fs.smartsupply.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusNotificationEvent {

    private Long orderId;
    private String status;      // e.g., CREATED, CONFIRMED, CANCELLED, COMPLETED
    private String customerEmail;
    private String message;     // Optional human-readable message

    private String triggeredByService; // e.g., order-service, payment-service
}
