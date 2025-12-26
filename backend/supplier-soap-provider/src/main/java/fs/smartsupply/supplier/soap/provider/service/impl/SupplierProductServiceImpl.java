package fs.smartsupply.supplier.soap.provider.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import com.smartsupply.supplier.soap.gen.AddSupplierProductRequest;
// import com.smartsupply.supplier.soap.gen.AddSupplierProductResponse;
import com.smartsupply.supplier.soap.gen.DeactivateSupplierProductRequest;
import com.smartsupply.supplier.soap.gen.DeactivateSupplierProductResponse;
import com.smartsupply.supplier.soap.gen.GetInventoryBulkRequest;
import com.smartsupply.supplier.soap.gen.GetInventoryBulkResponse;
import com.smartsupply.supplier.soap.gen.GetInventoryByProductRequest;
import com.smartsupply.supplier.soap.gen.GetInventoryByProductResponse;
import com.smartsupply.supplier.soap.gen.GetLowStockProductsRequest;
import com.smartsupply.supplier.soap.gen.GetLowStockProductsResponse;
import com.smartsupply.supplier.soap.gen.GetSupplierCatalogRequest;
import com.smartsupply.supplier.soap.gen.GetSupplierCatalogResponse;
import com.smartsupply.supplier.soap.gen.GetSupplierSummaryRequest;
import com.smartsupply.supplier.soap.gen.GetSupplierSummaryResponse;
import com.smartsupply.supplier.soap.gen.Inventory;
import com.smartsupply.supplier.soap.gen.SupplierProduct;
import com.smartsupply.supplier.soap.gen.SupplierSummary;
import com.smartsupply.supplier.soap.gen.UpdateStockRequest;
import com.smartsupply.supplier.soap.gen.UpdateStockResponse;

import fs.smartsupply.supplier.soap.provider.entity.InventoryEntity;
import fs.smartsupply.supplier.soap.provider.entity.SupplierEntity;
import fs.smartsupply.supplier.soap.provider.entity.SupplierProductEntity;
import fs.smartsupply.supplier.soap.provider.mapper.SupplierProductMapper;
import fs.smartsupply.supplier.soap.provider.mapper.XmlDateTimeMapper;
import fs.smartsupply.supplier.soap.provider.repository.InventoryRepository;
import fs.smartsupply.supplier.soap.provider.repository.SupplierProductRepository;
import fs.smartsupply.supplier.soap.provider.repository.SupplierRepository;
import fs.smartsupply.supplier.soap.provider.service.SupplierProductService;


@Service
@Transactional
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierRepository supplierRepo;
    private final SupplierProductRepository productRepo;
    private final InventoryRepository inventoryRepo;
    // private final SupplierProductMapper mapper;
    private final XmlDateTimeMapper dateTimeMapper;

    // public SupplierProductServiceImpl(XmlDateTimeMapper dateTimeMapper) {
    //     this.dateTimeMapper = dateTimeMapper;
    // }

    // public SupplierProductServiceImpl(SupplierProductMapper mapper) {
    //     this.mapper = mapper;
    // }

    public SupplierProductServiceImpl(
            SupplierRepository supplierRepo,
            SupplierProductRepository productRepo,
            InventoryRepository inventoryRepo, 
            XmlDateTimeMapper dateTimeMapper
        ) {
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
        this.inventoryRepo = inventoryRepo;
        this.dateTimeMapper = dateTimeMapper; 
    }

//     @Override
//     public AddSupplierProductResponse addProduct(
//             AddSupplierProductRequest request) {

//         // 1️⃣ Validate supplier exists
//         SupplierEntity supplier = supplierRepo.findById(request.getSupplierCode())
//             .orElseThrow(() ->
//                 new IllegalArgumentException("Supplier not found")
//             );

//         // 2️⃣ Prevent duplicates
//         productRepo.findBySupplierCodeAndName(
//             request.getSupplierCode(),
//             request.getName()
//         ).ifPresent(p -> {
//             new IllegalArgumentException("Product already exists");
//         });

//         // 3️⃣ Generate immutable reference
//         String supplierProductRef =
//             request.getSupplierCode() + "-" + UUID.randomUUID();

//         // 4️⃣ Persist product
//         SupplierProductEntity product = new SupplierProductEntity();
//         product.setSupplier(supplier);
//         product.setSupplierProductRef(supplierProductRef);
//         product.setName(request.getName());
//         product.setDescription(request.getDescription());
//         product.setStatus("ACTIVE");
//         product.setCreatedAt(Instant.now());
//         product.setUpdatedAt(Instant.now());

//         productRepo.save(product);

//         // 5️⃣ Initialize inventory
//         InventoryEntity inventory = new InventoryEntity();
//         inventory.setSupplierCode(request.getSupplierCode());
//         inventory.setSupplierProductRef(supplierProductRef);
//         inventory.setStock(request.getInitialStock());
//         inventory.setLowStockThreshold(
//             request.getLowStockThreshold()
//         );
//         inventory.setLastUpdatedAt(Instant.now());

//         inventoryRepo.save(inventory);

//         // 6️⃣ Respond
//         AddSupplierProductResponse response =
//             new AddSupplierProductResponse();
//         response.setSupplierProductRef(supplierProductRef);
//         response.setStatus("CREATED");

//         return response;
//     }

    // -----------------------------
    // 1️⃣ SUPPLIER CATALOG
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public GetSupplierCatalogResponse getCatalog(GetSupplierCatalogRequest request) {

        List<SupplierProductEntity> products =
                productRepo.findBySupplier_CodeAndStatus(
                        request.getSupplierCode(),
                        "ACTIVE"
                );

        GetSupplierCatalogResponse response = new GetSupplierCatalogResponse();
        products.forEach(p -> response.getProduct().add(mapProduct(p)));
        return response;
    }

    // -----------------------------
    // 2️⃣ INVENTORY BY PRODUCT
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public GetInventoryByProductResponse getInventory(
            GetInventoryByProductRequest request) {

        InventoryEntity inv = inventoryRepo.findBySupplierProductRef(
                request.getSupplierProductRef()
        ).orElseThrow(() ->
                new IllegalArgumentException("Product not found")
        );

        GetInventoryByProductResponse response =
                new GetInventoryByProductResponse();
        response.setInventory(mapInventory(inv));
        return response;
    }

    // -----------------------------
    // 3️⃣ INVENTORY BULK
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public GetInventoryBulkResponse getInventoryBulk(
            GetInventoryBulkRequest request) {

        List<InventoryEntity> inventories =
                inventoryRepo.findAllBySupplierCode(
                        request.getSupplierCode()
                );

        GetInventoryBulkResponse response =
                new GetInventoryBulkResponse();
        inventories.forEach(i -> response.getInventory().add(mapInventory(i)));
        return response;
    }

    // -----------------------------
    // 4️⃣ UPDATE STOCK
    // -----------------------------
    @Override
    public UpdateStockResponse updateStock(UpdateStockRequest request) {

        InventoryEntity inv = inventoryRepo.findBySupplierProductRef(
                request.getSupplierProductRef()
        ).orElseThrow(() ->
                new IllegalArgumentException("Product not found")
        );

        inv.setStock(request.getNewStock());
        inv.setLastUpdatedAt(Instant.now());
        inventoryRepo.save(inv);

        UpdateStockResponse response = new UpdateStockResponse();
        response.setStatus("UPDATED");
        return response;
    }

    // -----------------------------
    // 5️⃣ LOW STOCK PRODUCTS
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public GetLowStockProductsResponse getLowStock(
            GetLowStockProductsRequest request) {

        List<InventoryEntity> lowStock =
                inventoryRepo.findLowStockProducts(
                        request.getSupplierCode()
                );

        GetLowStockProductsResponse response =
                new GetLowStockProductsResponse();
        lowStock.forEach(i -> response.getInventory().add(mapInventory(i)));
        return response;
    }

    // -----------------------------
    // 6️⃣ SUPPLIER SUMMARY
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public GetSupplierSummaryResponse getSummary(
            GetSupplierSummaryRequest request) {

        List<InventoryEntity> all =
                inventoryRepo.findAllBySupplierCode(
                        request.getSupplierCode()
                );

        long lowStockCount =
                all.stream()
                   .filter(i -> i.getStock() <= i.getLowStockThreshold())
                   .count();

        SupplierSummary summary = new SupplierSummary();
        summary.setTotalProducts(all.size());
        summary.setLowStockCount((int) lowStockCount);
        summary.setLastInventorySync(
            dateTimeMapper.toXmlGregorianCalendar(
                all.stream()
                   .map(InventoryEntity::getLastUpdatedAt)
                   .max(Instant::compareTo)
                   .orElse(Instant.now())
            )
        );

        GetSupplierSummaryResponse response =
                new GetSupplierSummaryResponse();
        response.setSummary(summary);
        return response;
    }

    // -----------------------------
    // 7️⃣ DEACTIVATE PRODUCT
    // -----------------------------
    @Override
    public DeactivateSupplierProductResponse deactivateProduct(
            DeactivateSupplierProductRequest request) {

        SupplierProductEntity product =
                productRepo.findBySupplierProductRef(
                        request.getSupplierProductRef()
                ).orElseThrow(() ->
                        new IllegalArgumentException("Product not found")
                );

        product.setStatus("INACTIVE");
        product.setUpdatedAt(Instant.now());
        productRepo.save(product);

        DeactivateSupplierProductResponse response =
                new DeactivateSupplierProductResponse();
        response.setStatus("DEACTIVATED");
        return response;
    }

    // -----------------------------
    // MAPPERS (Anti-corruption)
    // -----------------------------
    private SupplierProduct mapProduct(SupplierProductEntity p) {
        SupplierProduct dto = new SupplierProduct();
        dto.setSupplierProductRef(p.getSupplierProductRef());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setStatus(p.getStatus());
        return dto;
    }

    private Inventory mapInventory(InventoryEntity i) {
        Inventory dto = new Inventory();
        dto.setSupplierProductRef(i.getSupplierProductRef());
        dto.setStock(i.getStock());
        dto.setLowStockThreshold(i.getLowStockThreshold());
        dto.setLastUpdatedAt(dateTimeMapper.toXmlGregorianCalendar(i.getLastUpdatedAt()));
        return dto;
    }
}
