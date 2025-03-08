package com.myorg.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {

    private int productId;

    private String productName;

    private String productDescription;

    private double productPrice;

    private int availableQuantity;
}
