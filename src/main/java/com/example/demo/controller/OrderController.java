package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{cartId}")
    public Order createOrder(@PathVariable Long cartId, @RequestBody Order order) {
        return orderService.createOrder(cartId, order.getAddress(),
                order.getFirstName(), order.getLastName(), order.getPaymentMethod(),
                order.getDeliveryMethod());
    }

    @GetMapping("/history-orders")
    public List<Order> getOrderHistory() {
        return orderService.getOrderHistory();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @PostMapping("/processing/{orderId}")
    public Order processOrder(@PathVariable Long orderId) {
        return orderService.processOrder(orderId);
    }
}
