package fs.smartsupply.common.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInitiatedEvent extends BaseEvent {
    private Long orderId;
    private Double amount;
    private String currency;

}
