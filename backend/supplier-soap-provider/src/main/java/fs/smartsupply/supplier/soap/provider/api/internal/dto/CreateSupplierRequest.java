package fs.smartsupply.supplier.soap.provider.api.internal.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSupplierRequest(
        @NotBlank String supplierCode,
        @NotBlank String name,
        String contactEmail
) {}
