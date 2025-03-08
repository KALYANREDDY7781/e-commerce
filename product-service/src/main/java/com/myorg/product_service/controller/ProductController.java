package com.myorg.product_service.controller;

import com.myorg.product_service.dto.ProductResponseDto;
import com.myorg.product_service.dto.ProductsDto;
import com.myorg.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductsDto productsDto){
        ProductResponseDto createdProduct = productService.createProduct(productsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id){
        ProductResponseDto product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable int id,@Valid @RequestBody ProductsDto product){
        ProductResponseDto product1 = productService.updateProduct(id,product);
        return ResponseEntity.status(HttpStatus.OK).body(product1);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProductById(id);
    }
}
