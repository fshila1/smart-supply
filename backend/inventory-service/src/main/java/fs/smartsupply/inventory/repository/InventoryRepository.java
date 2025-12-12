package fs.smartsupply.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fs.smartsupply.inventory.entity.ProductStock;

public interface InventoryRepository extends JpaRepository<ProductStock, String> {
    
}
