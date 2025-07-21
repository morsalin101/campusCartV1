package com.example.campusCart.serviceimpl;

import com.example.campusCart.dto.ProductDto;
import com.example.campusCart.model.Category;
import com.example.campusCart.model.Product;
import com.example.campusCart.repository.CategoryRepository;
import com.example.campusCart.repository.ProductRepository;
import com.example.campusCart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/media/";
// Removed duplicate uploadFile method

 
@Override
public void saveProduct(ProductDto productDto, MultipartFile file) {
    Product product = new Product();
    product.setTitle(productDto.getTitle());
    product.setStock(productDto.getStock());
    product.setPrice(productDto.getPrice());
    product.setDiscount(productDto.getDiscount());
    product.setDescription(productDto.getDescription());
    product.setInStock(productDto.isInStock());
    product.setActive(productDto.isActive());

    // Set category if selected
    if (productDto.getCategoryId() != null) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        product.setCategory(category);
    }

    // Upload image and save path
    if (file != null && !file.isEmpty()) {
        String fileName = uploadFile(file); // ⬅️ Will save the file in /static/media/
        product.setPicture(fileName); // ⬅️ Save just the filename or relative path
    }
   
    productRepository.save(product);
}


    @Override
    public void updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow();

        product.setTitle(dto.getTitle());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setDescription(dto.getDescription());
        product.setInStock(dto.isInStock());
        product.setActive(dto.isActive());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }

        if (!dto.getPicture().isEmpty()) {
            String fileName = uploadFile(dto.getPicture());
            product.setPicture(fileName);
        }

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    private String uploadFile(MultipartFile file) {
        String uniqueName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File uploadPath = new File(UPLOAD_DIR + uniqueName);
        try {
            file.transferTo(uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueName;
    }
}
