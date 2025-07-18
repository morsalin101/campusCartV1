package com.example.campusCart.controller;

import com.example.campusCart.dto.ProductDto;
import com.example.campusCart.model.Product;
import com.example.campusCart.service.CategoryService;
import com.example.campusCart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "dashboard/products";
    }

    @GetMapping("/add_product")
    public String showAddProductPage(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dashboard/add_product";
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute ProductDto productDto,
                              @RequestParam("file") MultipartFile file) {
        productService.saveProduct(productDto, file);
        return "redirect:/products";
    }

    @GetMapping("/edit_product/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .stock(product.getStock())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .description(product.getDescription())
                .inStock(product.isInStock())
                .active(product.isActive())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .build();

        model.addAttribute("productDto", dto);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dashboard/edit_product";
    }

    @PostMapping("/edit_product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return "redirect:/products";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
