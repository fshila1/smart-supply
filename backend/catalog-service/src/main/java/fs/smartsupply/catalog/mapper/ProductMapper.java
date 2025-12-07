package fs.smartsupply.catalog.mapper;

import org.springframework.stereotype.Component;

import fs.smartsupply.catalog.DTO.ProductRequest;
import fs.smartsupply.catalog.DTO.ProductResponse;
import fs.smartsupply.catalog.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest req) {
        Product p = new Product();
        p.setName(req.name());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setStock(req.stock());
        return p;
    }

    public void updateEntity(ProductRequest req, Product product) {
        product.setName(req.name());
        product.setDescription(req.description());
        product.setPrice(req.price());
        product.setStock(req.stock());
    }

    public ProductResponse toResponse(Product p) {
        return new ProductResponse(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getStock()
        );
    }
}