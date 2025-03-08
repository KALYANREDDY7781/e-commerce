package com.myorg.product_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductsDto {

    @NotBlank(message = "Product name can't be empty")
    private String productName;

    @NotBlank(message = "Product description can't be empty")
    private String productDescription;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private double productPrice;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private int availableQuantity;

}
