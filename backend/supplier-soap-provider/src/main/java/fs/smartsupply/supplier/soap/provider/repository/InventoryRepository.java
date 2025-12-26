package fs.smartsupply.supplier.soap.provider.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fs.smartsupply.supplier.soap.provider.entity.InventoryEntity;

@Repository
public interface InventoryRepository
        extends JpaRepository<InventoryEntity, UUID> {

    Optional<InventoryEntity> findBySupplierProductRef(String supplierProductRef);

    List<InventoryEntity> findAllBySupplierCode(String supplierCode);

    @Query("""
        select i from InventoryEntity i
        where i.stock <= i.lowStockThreshold
        and i.supplierCode = :supplierCode
    """)
    List<InventoryEntity> findLowStockProducts(
            @Param("supplierCode") String supplierCode
    );
}

// @Repository
// public interface InventoryRepository
//         extends JpaRepository<InventoryEntity, UUID> {

//     Optional<InventoryEntity> findBySupplierProductRef(String supplierProductRef);

//     @Query("""
//         SELECT i FROM Inventory i
//         JOIN SupplierProduct sp
//           ON i.supplierProductRef = sp.supplierProductRef
//         WHERE sp.supplier.code = :supplierCode
//           AND i.stock <= i.lowStockThreshold
//     """)
//     List<InventoryEntity> findLowStockProducts(String supplierCode);

//     @Query("""
//         SELECT i FROM Inventory i
//         JOIN SupplierProduct sp
//           ON i.supplierProductRef = sp.supplierProductRef
//         WHERE sp.supplier.code = :supplierCode
//     """)
//     List<InventoryEntity> findAllBySupplier(String supplierCode);
// }
