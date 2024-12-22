package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.OrderRequest;
import com.example.fooddelivery.entity.Order;
import com.example.fooddelivery.entity.OrderDetail;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setCourierId(request.getCourierId());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus(request.getStatus());

        List<OrderDetail> orderDetails = request.getOrderDetails().stream().map(detailRequest -> {
            OrderDetail detail = new OrderDetail();
            detail.setDishId(detailRequest.getDishId());
            detail.setQuantity(detailRequest.getQuantity());
            return detail;
        }).toList();

        Long orderId = orderService.createOrder(order, orderDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
}
