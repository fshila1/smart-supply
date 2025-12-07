package fs.smartsupply.catalog.DTO;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank
    @Size(max = 100)
    private String sku;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.000", inclusive = true)
    private BigDecimal weightKg;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(BigDecimal weightKg) {
        this.weightKg = weightKg;
    }
}