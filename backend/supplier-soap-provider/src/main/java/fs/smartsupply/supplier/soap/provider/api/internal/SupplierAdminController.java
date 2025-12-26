package fs.smartsupply.supplier.soap.provider.api.internal;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierResponse;
import fs.smartsupply.supplier.soap.provider.service.SupplierAdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/internal/api/suppliers")
public class SupplierAdminController {

    private final SupplierAdminService service;

    public SupplierAdminController(SupplierAdminService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateSupplierResponse> createSupplier(
            @RequestBody @Valid CreateSupplierRequest request) {

        return ResponseEntity.ok(service.createSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> listSuppliers() {
        return ResponseEntity.ok(service.listSuppliers());
    }
}
