package com.example.campusCart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/dashboard/add-category")
    public String addCategoryPage(Model model) {
        return "pages/addCategory"; // looks for templates/pages/addCategory.html
    }

    @GetMapping("/dashboard/all-category")
    public String allCategoryPage(Model model) {
        return "pages/allCategory"; // looks for templates/pages/allCategory.html
    }
}
