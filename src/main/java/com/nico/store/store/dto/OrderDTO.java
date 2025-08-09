package com.nico.store.store.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {
    private Long id;
    private Date orderDate;
    private Date shippingDate;
    private String orderStatus;
    private BigDecimal orderTotal;
    private UserDTO user;

    public OrderDTO() {}

    public OrderDTO(Long id, Date orderDate, Date shippingDate, String orderStatus, BigDecimal orderTotal, UserDTO user) {
        this.id = id;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public Date getShippingDate() { return shippingDate; }
    public void setShippingDate(Date shippingDate) { this.shippingDate = shippingDate; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public BigDecimal getOrderTotal() { return orderTotal; }
    public void setOrderTotal(BigDecimal orderTotal) { this.orderTotal = orderTotal; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
