package fs.smartsupply.catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fs.smartsupply.catalog.DTO.ProductRequest;
import fs.smartsupply.catalog.DTO.ProductResponse;
import fs.smartsupply.catalog.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        ProductResponse response = service.getById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable UUID id, @RequestBody ProductRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
