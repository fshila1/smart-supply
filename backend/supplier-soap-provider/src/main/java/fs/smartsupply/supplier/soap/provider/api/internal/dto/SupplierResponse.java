package fs.smartsupply.supplier.soap.provider.api.internal.dto;

import java.time.Instant;

public record SupplierResponse(
        String supplierCode,
        String name,
        String contactEmail,
        String status,
        Instant createdAt,
        Instant updatedAt
) {}
