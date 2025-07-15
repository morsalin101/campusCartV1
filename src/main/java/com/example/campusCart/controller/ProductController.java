package com.example.campusCart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/products")
    public String addProductPage() {
     return "dashboard/products";
    }

    @GetMapping("/dashboard/add_product")
    public String showAddProductPage(Model model) {
        return "dashboard/add_product";
    }

  
}
