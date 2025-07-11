package com.example.campusCart.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int stock;

    private double price;

    private String picture; // this should store the filename or path

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories;
}
