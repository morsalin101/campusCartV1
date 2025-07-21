package com.example.campusCart.model;

import jakarta.persistence.*;
import lombok.*;

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

    private double discount; 

    private String picture;

    @Lob
    private String description; 

    private boolean inStock; 

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
