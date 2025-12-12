package fs.smartsupply.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockReservationFailedEvent extends BaseEvent {
    private Long orderId;
    private String reason;
}
