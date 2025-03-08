package com.myorg.product_service.mapper;

import com.myorg.product_service.dto.ProductResponseDto;
import com.myorg.product_service.dto.ProductsDto;
import com.myorg.product_service.entity.Product;

public class ProductsMapper {

    public static Product mapToProduct(Product product,ProductsDto productsDto){
        product.setName(productsDto.getProductName());
        product.setDescription(productsDto.getProductDescription());
        product.setPrice(productsDto.getProductPrice());
        product.setStock(productsDto.getAvailableQuantity());
        return product;
    }
    public static ProductResponseDto convertToDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    /*public static ProductsDto mapToProductsDto(Product product,ProductsDto productsDto){
        productsDto.setProductName(product.getName());
       productsDto.setProductDescription(product.getDescription());
        productsDto.setProductPrice(product.getPrice());
       productsDto.setAvailableQuantity(product.getStock());
        return productsDto;
   }*/
}
