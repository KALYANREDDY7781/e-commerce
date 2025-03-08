package com.myorg.product_service.service;

import com.myorg.product_service.dto.ProductResponseDto;
import com.myorg.product_service.dto.ProductsDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(@Valid ProductsDto product);

    ProductResponseDto getProduct(int id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(int id, ProductsDto productsDto);

    void deleteProductById(int id);

}
