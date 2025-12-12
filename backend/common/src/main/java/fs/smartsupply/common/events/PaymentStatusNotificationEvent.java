package fs.smartsupply.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusNotificationEvent {

    private Long orderId;
    private String paymentStatus;   // e.g., INITIATED, COMPLETED, FAILED
    private Double amount;
    private String currency;
    private String customerEmail;
    private String message;         // Optional message for the customer

    private String triggeredByService; // e.g., payment-service
}
