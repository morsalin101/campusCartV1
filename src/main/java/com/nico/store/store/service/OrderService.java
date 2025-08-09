package com.nico.store.store.service;

import java.util.List;

import com.nico.store.store.domain.Order;
import com.nico.store.store.domain.Payment;
import com.nico.store.store.domain.Shipping;
import com.nico.store.store.domain.ShoppingCart;
import com.nico.store.store.domain.User;
import com.nico.store.store.dto.OrderDTO;

public interface OrderService {

    Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);

    List<Order> findByUser(User user);

    Order findOrderWithDetails(Long id);

    List<Order> findAll();

    boolean updateOrderStatus(Long id, String status);

    // 🔹 New method to fetch all orders with user and address details
    List<OrderDTO> findAllWithUsersAndAddress();
}
