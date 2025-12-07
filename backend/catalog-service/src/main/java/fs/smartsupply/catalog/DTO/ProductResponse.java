package fs.smartsupply.catalog.DTO;

public record ProductResponse(
        Long id,
        String name,
        String description,
        double price,
        int stock
) {}