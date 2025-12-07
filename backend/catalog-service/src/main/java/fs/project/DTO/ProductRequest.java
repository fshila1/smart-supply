package fs.project.DTO;

public record ProductRequest(
        String name,
        String description,
        double price,
        int stock
) {}
