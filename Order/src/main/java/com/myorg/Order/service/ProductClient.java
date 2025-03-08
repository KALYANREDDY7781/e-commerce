package com.myorg.Order.service;

import com.myorg.Order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8081/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable int id);

}
