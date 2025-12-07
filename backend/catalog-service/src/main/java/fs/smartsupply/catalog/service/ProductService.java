package fs.smartsupply.catalog.service;

import org.springframework.stereotype.Service;

import fs.smartsupply.catalog.DTO.ProductRequest;
import fs.smartsupply.catalog.DTO.ProductResponse;
import fs.smartsupply.catalog.entity.Product;
import fs.smartsupply.catalog.mapper.ProductMapper;
import fs.smartsupply.catalog.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repo, ProductMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ProductResponse> getAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProductResponse getById(UUID id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse create(ProductRequest req) {
        req.setSku(generateSku(req.getName()));
        Product product = mapper.toEntity(req);
        repo.save(product);
        return mapper.toResponse(product);
    }

    private String generateSku(String name) {
        // Example SKU pattern: ABC-20241205-8392
        String prefix = name == null ? "PRD" :
                name.replaceAll("[^A-Za-z0-9]", "")
                    .toUpperCase()
                    .substring(0, Math.min(3, name.length()));

        String random = String.valueOf((int) (Math.random() * 9000 + 1000));

        return prefix + "-" + System.currentTimeMillis() + "-" + random;
    }

    public ProductResponse update(UUID id, ProductRequest req) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(product != null) req.setSku(product.getSku());

        mapper.updateEntity(req, product);
        repo.save(product);

        return mapper.toResponse(product);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
