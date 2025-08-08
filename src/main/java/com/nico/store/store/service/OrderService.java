package com.nico.store.store.service;

import java.util.List;

import com.nico.store.store.domain.Order;
import com.nico.store.store.domain.Payment;
import com.nico.store.store.domain.Shipping;
import com.nico.store.store.domain.ShoppingCart;
import com.nico.store.store.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);

	List<Order> findAll();
<<<<<<< HEAD

 boolean updateOrderStatus(Long id, String status);

=======
    boolean updateOrderStatus(Long id,String status);
>>>>>>> f872a8354c26e91c25abb59b16386f917ba1088e
}
