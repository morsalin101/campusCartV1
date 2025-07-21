package com.example.campusCart.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private int stock;
    private double price;
    private double discount;
    private String description;
    private boolean inStock;
    private boolean active;
    private Long categoryId; // assuming category is selected by id
    private MultipartFile picture; // for file upload
}
