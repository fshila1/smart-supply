package fs.smartsupply.supplier.soap.provider.api.internal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSupplierProductRequest(
        @NotBlank String supplierCode,
        @NotBlank String supplierProductRef,
        @NotBlank String name,
        String description,
        @NotNull Integer initialStock,
        @NotNull Integer lowStockThreshold
) {}
