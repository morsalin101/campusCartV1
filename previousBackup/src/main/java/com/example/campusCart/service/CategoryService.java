package com.example.campusCart.service;

import com.example.campusCart.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    void saveCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories(); // For displaying all categories

    void deleteCategory(Long id); // For deleting a category
}
