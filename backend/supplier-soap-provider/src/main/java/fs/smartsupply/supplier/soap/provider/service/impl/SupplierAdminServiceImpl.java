package fs.smartsupply.supplier.soap.provider.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierResponse;
import fs.smartsupply.supplier.soap.provider.entity.SupplierEntity;
import fs.smartsupply.supplier.soap.provider.repository.SupplierRepository;
import fs.smartsupply.supplier.soap.provider.service.SupplierAdminService;

@Service
@Transactional
public class SupplierAdminServiceImpl implements SupplierAdminService {

    private final SupplierRepository supplierRepo;

    public SupplierAdminServiceImpl(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @Override
    public CreateSupplierResponse createSupplier(CreateSupplierRequest request) {

        if (supplierRepo.existsById(request.supplierCode())) {
            new IllegalArgumentException("Supplier already exists");
        }

        SupplierEntity supplier = new SupplierEntity();
        supplier.setCode(request.supplierCode());
        supplier.setName(request.name());
        supplier.setContactEmail(request.contactEmail());
        supplier.setCreatedAt(Instant.now());
        supplier.setStatus("CREATED");
        supplier.setUpdatedAt(Instant.now());

        supplierRepo.save(supplier);

        return new CreateSupplierResponse(
                supplier.getCode(),
                "CREATED"
        );
    }

    @Override
    public List<SupplierResponse> listSuppliers() {
        return supplierRepo.findAll().stream()
            .map(s -> new SupplierResponse(
                    s.getCode(),
                    s.getName(),
                    s.getContactEmail(),
                    s.getStatus(),
                    s.getCreatedAt(),
                    s.getUpdatedAt()
            ))
            .toList();
    }
}
