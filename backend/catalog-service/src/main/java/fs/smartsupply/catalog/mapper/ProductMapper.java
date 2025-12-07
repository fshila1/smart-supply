package fs.smartsupply.catalog.mapper;

import org.springframework.stereotype.Component;

import fs.smartsupply.catalog.DTO.ProductRequest;
import fs.smartsupply.catalog.DTO.ProductResponse;
import fs.smartsupply.catalog.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest req) {
        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setUnitPrice(req.getUnitPrice());
        p.setWeightKg(req.getWeightKg());
        p.setSku(req.getSku());
        return p;
    }

    public void updateEntity(ProductRequest req, Product product) {
        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setUnitPrice(req.getUnitPrice());
        product.setWeightKg(req.getWeightKg());
        product.setSku(req.getSku());
    }

    public ProductResponse toResponse(Product p) {
        return new ProductResponse(
            p.getId(),
            p.getSku(),
            p.getName(),
            p.getDescription(),
            p.getUnitPrice(),
            p.getWeightKg(),
            p.getCreatedAt(),
            p.getUpdatedAt()
        );
    }
}