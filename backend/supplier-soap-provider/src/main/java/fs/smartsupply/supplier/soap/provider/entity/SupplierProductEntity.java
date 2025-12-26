package fs.smartsupply.supplier.soap.provider.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier_product")
@Getter
@Setter
public class SupplierProductEntity {
    @Id
    private UUID id;
    private String supplierProductRef;
    private String name;
    private String description;
    private String status;
    @ManyToOne
    @JoinColumn(name = "supplier_code")
    private SupplierEntity supplier;
    private Instant createdAt;
    private Instant updatedAt;
}
