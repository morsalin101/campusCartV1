package com.nico.store.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nico.store.store.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

 @GetMapping("/order-list")
public String getAllOrders(Model model) {
 model.addAttribute("orders", orderService.findAll());
 return "order-list"; // Update this to your Thymeleaf page for listing all orders
}
}