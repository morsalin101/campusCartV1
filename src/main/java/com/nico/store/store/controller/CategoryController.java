package com.nico.store.store.controller;

import com.nico.store.store.domain.Category;
import com.nico.store.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Show category list
    @GetMapping("/list")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-list";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    // Handle save
    @PostMapping("/save")
    public String saveCategory(@RequestParam String name) {
        categoryService.save(name);
        return "redirect:/category/list";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        model.addAttribute("category", category);
        return "redirect:/category/list"; // Redirect to the list after fetching the category
    }

    // Handle update
    @PostMapping("/update")
    public String updateCategory(@RequestParam Long id, @RequestParam String name) {
        categoryService.update(id, name);
        return "redirect:/category/list";
    }

    // Handle delete
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/category/list";
    }
}
