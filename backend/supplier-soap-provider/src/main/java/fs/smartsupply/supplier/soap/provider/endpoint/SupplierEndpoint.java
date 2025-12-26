package fs.smartsupply.supplier.soap.provider.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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

import fs.smartsupply.supplier.soap.provider.service.SupplierProductService;

@Endpoint
public class SupplierEndpoint {

    private static final String NAMESPACE_URI = "http://supplier.com/schema";

    @Autowired
    private SupplierProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupplierCatalogRequest")
    @ResponsePayload
    public GetSupplierCatalogResponse getCatalog(@RequestPayload GetSupplierCatalogRequest request) {
        return productService.getCatalog(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInventoryByProductRequest")
    @ResponsePayload
    public GetInventoryByProductResponse getInventory(@RequestPayload GetInventoryByProductRequest request) {
        return productService.getInventory(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateStockRequest")
    @ResponsePayload
    public UpdateStockResponse updateStock(@RequestPayload UpdateStockRequest request) {
        return productService.updateStock(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetLowStockProductsRequest")
    @ResponsePayload
    public GetLowStockProductsResponse getLowStock(@RequestPayload GetLowStockProductsRequest request) {
        return productService.getLowStock(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupplierSummaryRequest")
    @ResponsePayload
    public GetSupplierSummaryResponse getSummary(@RequestPayload GetSupplierSummaryRequest request) {
        return productService.getSummary(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeactivateSupplierProductRequest")
    @ResponsePayload
    public DeactivateSupplierProductResponse deactivate(@RequestPayload DeactivateSupplierProductRequest request) {
        return productService.deactivateProduct(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInventoryBulkRequest")
    @ResponsePayload
    public GetInventoryBulkResponse getInventoryBulk(@RequestPayload GetInventoryBulkRequest request) {
        return productService.getInventoryBulk(request);
    }

    // @PayloadRoot(
    //     namespace = NAMESPACE_URI,
    //     localPart = "AddSupplierProductRequest"
    // )
    // @ResponsePayload
    // public AddSupplierProductResponse addSupplierProduct(
    //         @RequestPayload AddSupplierProductRequest request) {
    //     return productService.addProduct(request);
    // }
}



// import org.springframework.ws.server.endpoint.annotation.Endpoint;
// import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
// import org.springframework.ws.server.endpoint.annotation.RequestPayload;
// import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// import com.smartsupply.supplier.soap.gen.GetSupplierRequest;
// import com.smartsupply.supplier.soap.gen.GetSupplierResponse;


// @Endpoint
// public class SupplierEndpoint {

//     private static final String NAMESPACE_URI =
//             "http://smartsupply.com/supplier";

//     @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupplierRequest")
//     @ResponsePayload
//     public GetSupplierResponse getSupplier(
//             @RequestPayload GetSupplierRequest request) {

//         GetSupplierResponse response = new GetSupplierResponse();
//         response.setId(request.getSupplierId());
//         response.setName("Demo Supplier");
//         response.setStatus("ACTIVE");
//         return response;
//     }
// }
