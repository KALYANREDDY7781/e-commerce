package com.myorg.Order.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private int itemId;
    private int productId;  // Product ID from the Product Service
    @Min(value = 1,message = "Quantity should be atleast 1")
    private int quantity;  // Quantity of the product ordered
    @Min(value = 1,message = "Amount should be greater than 0")
    private double price;  // Price of a single unit of the product
}
