package fs.smartsupply.catalog.DTO;

public record ProductRequest(
        String name,
        String description,
        double price,
        int stock
) {}
