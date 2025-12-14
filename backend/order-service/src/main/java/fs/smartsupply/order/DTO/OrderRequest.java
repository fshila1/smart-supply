package fs.smartsupply.order.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import fs.smartsupply.common.model.OrderItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private UUID userId;

    // private OrderStatus status;

    private BigDecimal totalAmount;

    private String currency;

    private List<OrderItem> items;
}