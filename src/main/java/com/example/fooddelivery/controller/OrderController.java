package com.example.fooddelivery.controller;

import com.example.fooddelivery.entity.Order;
import com.example.fooddelivery.entity.OrderDetail;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/new")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "create-order";
    }

    @PostMapping("/new")
    public String createOrder(@ModelAttribute Order order) {
        List<Long> dishIdList = List.of(1L, 2L);

        List<OrderDetail> orderDetails = dishIdList.stream().map(dishId -> {
            OrderDetail detail = new OrderDetail();
            detail.setDishId(dishId);
            detail.setQuantity(2);
            return detail;
        }).toList();
        order.setStatus("Очікує обробки");
        orderService.createOrder(order, orderDetails);

        return "redirect:/orders/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "edit-order";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        orderService.updateOrderStatus(id, order.getStatus());
        return "redirect:/orders/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }

    @GetMapping("/search")
    public String searchOrderById(@RequestParam Long id, Model model) {
        try {
            Order order = orderService.getOrderById(id);
            model.addAttribute("order", order);
            return "order-details";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Order not found with ID: " + id);
            return "orders";
        }
    }
}
