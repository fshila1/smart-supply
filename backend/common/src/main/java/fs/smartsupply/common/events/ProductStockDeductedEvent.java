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
public class ProductStockDeductedEvent {
    // private String sku;
    // private Integer deductedQty;
    // private Integer availableQtyAfter;
    private Long orderId;
    private List<OrderItem> items;
}
