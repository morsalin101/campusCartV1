package com.nico.store.store.service;

import com.nico.store.store.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    void save(Category category);
    void delete(Long id);
}
