package com.nico.store.store.controller;

import com.nico.store.store.dto.OrderDTO;
import com.nico.store.store.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/article/allorders")
    public ResponseEntity<List<OrderDTO>> allOrders() {
        List<OrderDTO> orders = orderService.findAllWithUsersAndAddress();
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
}
