package fs.smartsupply.common.events;

import java.util.List;

import fs.smartsupply.common.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockReleasedEvent extends BaseEvent {
    private Long orderId;
    private List<OrderItem> items;

}
