package fs.smartsupply.supplier.soap.provider.service;

import java.util.List;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierResponse;

public interface SupplierAdminService {
    CreateSupplierResponse createSupplier(CreateSupplierRequest request);
    List<SupplierResponse> listSuppliers();
}
