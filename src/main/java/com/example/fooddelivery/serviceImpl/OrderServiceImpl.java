package com.example.fooddelivery.serviceImpl;

import com.example.fooddelivery.entity.Order;
import com.example.fooddelivery.entity.OrderDetail;
import com.example.fooddelivery.repository.OrderDetailRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Long createOrder(Order order, List<OrderDetail> orderDetails) {
        Order savedOrder = orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {
            detail.setOrderId(savedOrder.getId()); // Устанавливаем ID заказа для каждой детали
            orderDetailRepository.save(detail);
        }

        return savedOrder.getId();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public void updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderDetailRepository.deleteAll(orderDetailRepository.findAllByOrderId(id));
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
