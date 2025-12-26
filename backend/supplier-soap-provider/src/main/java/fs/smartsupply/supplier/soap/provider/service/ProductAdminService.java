package fs.smartsupply.supplier.soap.provider.service;

import java.util.List;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierProductResponse;

public interface ProductAdminService {
    CreateSupplierProductResponse createProduct(CreateSupplierProductRequest request);
    List<SupplierProductResponse> listProducts();
}
