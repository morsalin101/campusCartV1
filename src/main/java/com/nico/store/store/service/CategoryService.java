package com.nico.store.store.service;

import com.nico.store.store.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void save(String name);

    void update(Long id, String name);

    void delete(Long id);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);
}
