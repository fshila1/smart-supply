package fs.smartsupply.supplier.soap.provider.service;

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
import com.smartsupply.supplier.soap.gen.UpdateStockRequest;
import com.smartsupply.supplier.soap.gen.UpdateStockResponse;

public interface SupplierProductService {

    // AddSupplierProductResponse addProduct(AddSupplierProductRequest request);

    GetSupplierCatalogResponse getCatalog(GetSupplierCatalogRequest request);

    GetInventoryByProductResponse getInventory(GetInventoryByProductRequest request);

    GetInventoryBulkResponse getInventoryBulk(GetInventoryBulkRequest request);

    UpdateStockResponse updateStock(UpdateStockRequest request);

    GetLowStockProductsResponse getLowStock(GetLowStockProductsRequest request);

    GetSupplierSummaryResponse getSummary(GetSupplierSummaryRequest request);

    DeactivateSupplierProductResponse deactivateProduct(
            DeactivateSupplierProductRequest request
    );
}
