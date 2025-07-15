package com.example.campusCart.controller;

import com.example.campusCart.dto.CategoryDto;
import com.example.campusCart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String addCategoryPage() {
        return "dashboard/categories";
    }

   @GetMapping("/dashboard/add_category")
    public String showAddCategoryPage(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "dashboard/add_category";
    }

    // Save New Category
    @PostMapping("categories/save")
    public String saveCategory(@ModelAttribute CategoryDto categoryDto,
                               RedirectAttributes redirectAttributes) {
        try {
            categoryService.saveCategory(categoryDto);
            redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
        }
        return "redirect:dashboard/add_category";
    }

    // Show All Categories Page
   @GetMapping("categories/all")
public String showAllCategories(Model model) {
    List<CategoryDto> categories = categoryService.getAllCategories();

    model.addAttribute("categories", categories);
    return "pages/allCategory";
}


    // Update Category
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute CategoryDto categoryDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(categoryDto);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Update failed.");
        }
        return "redirect:/categories/all";
    }

    // Delete Category
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Delete failed.");
        }
        return "redirect:/categories/all";
    }
}
