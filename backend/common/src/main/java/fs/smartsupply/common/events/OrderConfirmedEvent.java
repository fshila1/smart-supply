package fs.smartsupply.common.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderConfirmedEvent extends BaseEvent {
    private Long orderId;
    private Double totalAmount;

}
