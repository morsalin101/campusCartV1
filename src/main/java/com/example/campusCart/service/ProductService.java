package com.example.campusCart.service;

import com.example.campusCart.dto.ProductDto;
import com.example.campusCart.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto, MultipartFile file);
    void updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
