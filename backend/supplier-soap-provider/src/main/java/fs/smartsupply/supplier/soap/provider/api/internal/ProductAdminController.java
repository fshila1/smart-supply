package fs.smartsupply.supplier.soap.provider.api.internal;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductRequest;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.CreateSupplierProductResponse;
import fs.smartsupply.supplier.soap.provider.api.internal.dto.SupplierProductResponse;
import fs.smartsupply.supplier.soap.provider.service.ProductAdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/internal/api/products")
public class ProductAdminController {

    private final ProductAdminService service;

    public ProductAdminController(ProductAdminService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateSupplierProductResponse> createProduct(
            @RequestBody @Valid CreateSupplierProductRequest request) {

        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<SupplierProductResponse>> listProducts() {
        return ResponseEntity.ok(service.listProducts());
    }
}
