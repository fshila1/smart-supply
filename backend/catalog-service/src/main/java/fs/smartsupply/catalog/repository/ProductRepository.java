package fs.smartsupply.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fs.smartsupply.catalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
