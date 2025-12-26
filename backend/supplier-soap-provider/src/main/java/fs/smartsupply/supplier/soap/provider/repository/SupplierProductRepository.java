package fs.smartsupply.supplier.soap.provider.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fs.smartsupply.supplier.soap.provider.entity.SupplierProductEntity;

@Repository
public interface SupplierProductRepository
        extends JpaRepository<SupplierProductEntity, UUID> {

    Optional<SupplierProductEntity> findBySupplierProductRef(String supplierProductRef);

    List<SupplierProductEntity> findBySupplier_CodeAndStatus(
            String supplierCode,
            String status
    );

//     Optional<SupplierProductEntity> findBySupplierCodeAndName(String supplierCode, String name);
}
