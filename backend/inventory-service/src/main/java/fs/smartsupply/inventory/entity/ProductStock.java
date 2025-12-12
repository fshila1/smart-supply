package fs.smartsupply.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "product_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStock {

    @Id
    @Column(name = "sku", length = 100, nullable = false, updatable = false)
    private String sku;

    @Column(name = "total_qty", nullable = false)
    private int totalQty;

    @Column(name = "reserved_qty", nullable = false)
    private int reservedQty;

    @Column(name = "available_qty", nullable = false)
    private int availableQty;

    @Column(name = "threshold", nullable = false)
    private int threshold;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = OffsetDateTime.now();
    }
}