package fs.smartsupply.common.events;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCompletedEvent extends BaseEvent {
    private Long orderId;
    private String transactionId;
    private Double amount;
    private String currency;
}
