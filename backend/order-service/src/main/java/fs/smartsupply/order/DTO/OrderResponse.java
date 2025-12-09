package fs.smartsupply.order.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private UUID userId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private String currency;

    private UUID correlationId;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}