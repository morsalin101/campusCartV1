package com.nico.store.store.controller;

import com.nico.store.store.domain.Category;
import com.nico.store.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private final Path UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/images/category/");

    // List categories page
    @GetMapping("/list")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category-list";
    }

    // Show add category form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    // Save new category
   @PostMapping("/save")
public String saveCategory(@RequestParam("name") String name,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes) {

    String imageFilename = null;

    try {
        if (imageFile != null && !imageFile.isEmpty()) {
            Files.createDirectories(UPLOAD_DIR);
            imageFilename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = UPLOAD_DIR.resolve(imageFilename);
            imageFile.transferTo(filePath.toFile());
        }
    } catch (IOException e) {
        e.printStackTrace();
        return "error";
    }

    Category category = new Category();
    category.setName(name);
    category.setImage(imageFilename);
    categoryService.save(category);

    // Add flash attribute to survive redirect
    redirectAttributes.addFlashAttribute("success", true);

    return "redirect:/category/add";  // Assuming your add form mapping is /category/add
}


    // Update category
    @PostMapping("/update")
    public String updateCategory(@RequestParam("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(name);

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                // Delete old image file if exists
                if (category.getImage() != null) {
                    Path oldImagePath = UPLOAD_DIR.resolve(category.getImage());
                    Files.deleteIfExists(oldImagePath);
                }

                Files.createDirectories(UPLOAD_DIR);
                String imageFilename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path filePath = UPLOAD_DIR.resolve(imageFilename);
                imageFile.transferTo(filePath.toFile());
                category.setImage(imageFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        categoryService.save(category);
        return "redirect:/category/list";
    }

    // Delete category — use POST for safe delete
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {

        // Delete image file if exists
        categoryService.getCategoryById(id).ifPresent(category -> {
            if (category.getImage() != null) {
                Path imagePath = UPLOAD_DIR.resolve(category.getImage());
                try {
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        categoryService.delete(id);
        return "redirect:/category/list";
    }
}
