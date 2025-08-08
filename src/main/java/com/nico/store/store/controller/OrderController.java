package com.nico.store.store.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nico.store.store.domain.Order;
import com.nico.store.store.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/article/allorders")
    public ResponseEntity<List<Order>> allOrders() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/article/orders/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id,
                                                  @RequestBody String statusJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(statusJson);
            String status = node.get("orderStatus").asText();

            boolean updated = orderService.updateOrderStatus(id, status);
            if (updated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
<<<<<<< HEAD
   }


=======

}
>>>>>>> f872a8354c26e91c25abb59b16386f917ba1088e
