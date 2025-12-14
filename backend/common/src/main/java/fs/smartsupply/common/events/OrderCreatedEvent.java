package fs.smartsupply.common.events;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import fs.smartsupply.common.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent extends BaseEvent {
    private Long orderId;
    private UUID userId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private String currency;
}
