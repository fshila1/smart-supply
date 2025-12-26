package fs.smartsupply.supplier.soap.provider.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_products")
public class SupplierProductEntity_old {

    @Id
    private String id;

    private String name;

    private int stock;

    @Column(name = "low_stock_threshold")
    private int lowStockThreshold;

    @Column(name = "supplier_id")
    private String supplierId;

    // getters/setters
}
