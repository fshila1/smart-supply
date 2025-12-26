package fs.smartsupply.supplier.soap.provider.api.internal.dto;

import java.time.Instant;

public record SupplierProductResponse(
        String supplierProductRef,
        String supplierCode,
        String name,
        String description,
        String status,
        Instant createdAt,
        Instant updatedAt
) {}
