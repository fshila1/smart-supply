package fs.project.service;

import org.springframework.stereotype.Service;

import fs.project.DTO.ProductRequest;
import fs.project.DTO.ProductResponse;
import fs.project.entity.Product;
import fs.project.mapper.ProductMapper;
import fs.project.repository.ProductRepository;

import java.util.List;

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

    public ProductResponse getById(Long id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse create(ProductRequest req) {
        Product product = mapper.toEntity(req);
        repo.save(product);
        return mapper.toResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        mapper.updateEntity(req, product);
        repo.save(product);

        return mapper.toResponse(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
