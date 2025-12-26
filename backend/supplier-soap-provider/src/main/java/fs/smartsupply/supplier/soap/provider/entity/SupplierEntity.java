package fs.smartsupply.supplier.soap.provider.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class SupplierEntity {
    @Id
    private String code;
    private String name;
    private String contactEmail;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}


