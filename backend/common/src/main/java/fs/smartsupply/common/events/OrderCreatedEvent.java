package fs.smartsupply.common.events;

import java.util.List;

import fs.smartsupply.common.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent extends BaseEvent {
    private Long orderId;
    private Long userId;
    private List<OrderItem> items;
    private Double totalAmount;
    private String currency;
}
