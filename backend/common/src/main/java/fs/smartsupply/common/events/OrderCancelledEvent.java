package fs.smartsupply.common.events;

import java.util.List;

import fs.smartsupply.common.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCancelledEvent extends BaseEvent {
    private Long orderId;
    private List<OrderItem> items;
    private String reason;
}
