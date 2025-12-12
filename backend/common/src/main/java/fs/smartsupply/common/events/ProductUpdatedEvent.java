package fs.smartsupply.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdatedEvent extends BaseEvent {

    private Long productId;
    private String sku;
    private String name;

    private Double price;
    private Integer availableQty;

    private String updatedByService; // catalog-service, inventory-service, supplier-sync etc.
}
