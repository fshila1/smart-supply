package fs.smartsupply.supplier.soap.provider.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "supplier_code", nullable = false)
    private String supplierCode;

    @Column(name = "supplier_product_ref", nullable = false)
    private String supplierProductRef;

    private int stock;
    private int lowStockThreshold;
    private Instant lastUpdatedAt;
}
