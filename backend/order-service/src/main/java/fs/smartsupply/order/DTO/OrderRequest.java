package fs.smartsupply.order.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private UUID userId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private String currency;
}