package fs.smartsupply.supplier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fs.smartsupply.supplier.DTO.SupplierResponseDTO;
import fs.smartsupply.supplier.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplier(
            @PathVariable String id) {

        return ResponseEntity.ok(
                supplierService.getSupplier(id)
        );
    }
}
