package com.myorg.product_service.service;

import com.myorg.product_service.dto.ProductResponseDto;
import com.myorg.product_service.dto.ProductsDto;
import com.myorg.product_service.entity.Product;
import com.myorg.product_service.exception.DuplicateProductException;
import com.myorg.product_service.exception.ProductNotFoundException;
import com.myorg.product_service.mapper.ProductsMapper;
import com.myorg.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductsDto productsDto) {
        Product product = ProductsMapper.mapToProduct(new Product(),productsDto);
        Optional<Product> p = productRepository.findByName(product.getName());
        if(p.isPresent()){
            throw new DuplicateProductException("Product already exists with name: "+product.getName());
        }
        log.info("Product doesn't exist. Creating new Product!!");
        Product savedProduct = productRepository.save(product);
        log.info("Created new Product with Name: "+savedProduct.getName()+" and ID:"+savedProduct.getId());
        return new ProductResponseDto(savedProduct.getId(),savedProduct.getName(),savedProduct.getDescription(),
                savedProduct.getPrice(),savedProduct.getStock());
    }

    @Override
    public ProductResponseDto getProduct(int id) {
        //int a = 2/0;
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            log.error("Product not found with ID: "+id);
            throw new ProductNotFoundException("Product not found with ID: "+id);
        }
        log.info("Product found with ID: "+id);
        return ProductsMapper.convertToDto(p.get());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> ProductsMapper.convertToDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(int id, ProductsDto updatedProduct) {
        /*Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            Product existingProduct = product.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setPrice(updatedProduct.getPrice());
            productRepository.save(existingProduct);
        }*/
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            log.error("Product with ID {} not found", id);
            throw new ProductNotFoundException("Product not found with ID: "+id);
        }
        log.info("Updating product with ID: {}", id);
        Product existingProduct = p.get();
        existingProduct.setName(updatedProduct.getProductName());
        existingProduct.setDescription(updatedProduct.getProductDescription());
        existingProduct.setPrice(updatedProduct.getProductPrice());
        existingProduct.setStock(updatedProduct.getAvailableQuantity());

        productRepository.save(existingProduct);
        log.info("Product updated successfully with name: {}", existingProduct.getName());
        return ProductsMapper.convertToDto(existingProduct);

    }

    @Override
    public void deleteProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            log.error("Product with ID {} not found", id);
            throw new ProductNotFoundException("Product not found with ID: "+id);
        }
        productRepository.delete(product.get());
        log.info("Product has been deleted with ID: "+id);
    }
}
