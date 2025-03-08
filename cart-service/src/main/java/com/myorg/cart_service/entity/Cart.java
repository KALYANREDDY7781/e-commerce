package com.myorg.cart_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="cart_items")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer cartItemId; // Unique ID for each cart item

    private Integer userId; // ID of the user who owns the cart

    private Integer productId; // ID of the product (should match productId in Product Service)

    private String productName; // Store product name (redundant but avoids extra calls)

    private String productDescription; // Store product description

    private String productPrice; // Store price at the time of adding to cart

    private Integer quantity; // Number of units added to the cart

    private LocalDateTime addedAt; // Timestamp when the product was added

}
