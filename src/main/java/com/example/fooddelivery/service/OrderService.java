package com.example.fooddelivery.service;

import com.example.fooddelivery.entity.Order;
import com.example.fooddelivery.entity.OrderDetail;
import java.util.List;

public interface OrderService {
    Long createOrder(Order order, List<OrderDetail> orderDetails);
    Order getOrderById(Long id);
    void updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
    List<Order> getOrdersByStatus(String status);
}
