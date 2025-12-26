package fs.smartsupply.supplier.soap.provider.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierProductResponse;
import fs.smartsupply.supplier.soap.provider.entity.InventoryEntity;
import fs.smartsupply.supplier.soap.provider.entity.SupplierEntity;
import fs.smartsupply.supplier.soap.provider.entity.SupplierProductEntity;
import fs.smartsupply.supplier.soap.provider.repository.InventoryRepository;
import fs.smartsupply.supplier.soap.provider.repository.SupplierProductRepository;
import fs.smartsupply.supplier.soap.provider.repository.SupplierRepository;
import fs.smartsupply.supplier.soap.provider.service.ProductAdminService;

@Service
@Transactional
public class ProductAdminServiceImpl implements ProductAdminService {

    private final SupplierRepository supplierRepo;
    private final SupplierProductRepository productRepo;
    private final InventoryRepository inventoryRepo;

    public ProductAdminServiceImpl(
            SupplierRepository supplierRepo,
            SupplierProductRepository productRepo,
            InventoryRepository inventoryRepo
    ) {
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public CreateSupplierProductResponse createProduct(
            CreateSupplierProductRequest request) {

        SupplierEntity supplier = supplierRepo.findById(request.supplierCode())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));

        SupplierProductEntity product = new SupplierProductEntity();
        product.setId(UUID.randomUUID());
        product.setSupplierProductRef(request.supplierProductRef());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setStatus("ACTIVE");
        product.setSupplier(supplier);
        product.setCreatedAt(Instant.now());

        productRepo.save(product);

        InventoryEntity inventory = new InventoryEntity();
        inventory.setSupplierProductRef(product.getSupplierProductRef());
        inventory.setSupplierCode(request.supplierCode());
        inventory.setStock(request.initialStock());
        inventory.setLowStockThreshold(request.lowStockThreshold());
        inventory.setLastUpdatedAt(Instant.now());

        inventoryRepo.save(inventory);

        return new CreateSupplierProductResponse(
                product.getSupplierProductRef(),
                "CREATED"
        );
    }

    @Override
    public List<SupplierProductResponse> listProducts() {
        return productRepo.findAll().stream()
            .map(p -> new SupplierProductResponse(
                    p.getSupplierProductRef(),
                    p.getSupplier().getCode(),
                    p.getName(),
                    p.getDescription(),
                    p.getStatus(),
                    p.getCreatedAt(),
                    p.getUpdatedAt()
            ))
            .toList();
    }
}
