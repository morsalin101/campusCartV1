package com.example.campusCart.controller;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {

    @GetMapping({"/", "/dashboard/index"})
    public String dashboardHome(Model model, Principal principal) {
        return "dashboard/index";
    }

   
    @GetMapping("/dashboard/add-product")
    public String addProductPage(Model model) {
    return "pages/addProduct";
}
}
