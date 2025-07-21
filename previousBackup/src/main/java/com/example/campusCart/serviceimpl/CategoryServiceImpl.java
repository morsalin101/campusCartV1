package com.example.campusCart.serviceimpl;

import com.example.campusCart.dto.CategoryDto;
import com.example.campusCart.model.Category;
import com.example.campusCart.repository.CategoryRepository;
import com.example.campusCart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
