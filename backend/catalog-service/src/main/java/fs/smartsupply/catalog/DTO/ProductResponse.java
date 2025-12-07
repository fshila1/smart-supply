package fs.smartsupply.catalog.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private UUID id;

    private String sku;

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private BigDecimal weightKg;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}