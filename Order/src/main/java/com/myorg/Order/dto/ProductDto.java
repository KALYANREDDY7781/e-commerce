package com.myorg.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;

    private String productDescription;

    private double productPrice;

    private int quantity;


}
