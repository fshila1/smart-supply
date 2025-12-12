package fs.smartsupply.common.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFailedEvent extends BaseEvent {
    private Long orderId;
    private String reason;

    // getters and setters
}
