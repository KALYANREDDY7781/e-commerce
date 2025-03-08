package com.myorg.product_service.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Table(name="products")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name="p_name")
    private String name;

    @Column(name="p_description")
    private String description;

    @Column(name="p_price")
    private double price;

    @Column(name="p_stock")
    private int stock;


}
