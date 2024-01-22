package com.example.demo.service;

import com.example.demo.client.CartServiceClient;
import com.example.demo.client.ProductServiceClient;
import com.example.demo.enumy.OrderStatus;
import com.example.demo.exceptions.OrderNotFoundException;
import com.example.demo.model.CartDetails;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient;

    public List<Order> getOrderHistory() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        log.info("Fetching order by ID: {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Transactional
    public Order createOrder(Long cartId, String address, String firstName, String lastName, String paymentMethod, String deliveryMethod) {
        CartDetails cartDetails = cartServiceClient.getCartDetails(cartId);
        log.info("Creating order for cart ID: {}, address: {}, firstName: {}, lastName: {}, paymentMethod: {}, deliveryMethod: {}",
                cartId, address, firstName, lastName, paymentMethod, deliveryMethod);

        Order order = Order.builder()
                .cartId(cartId)
                .address(address)
                .firstName(firstName)
                .lastName(lastName)
                .paymentMethod(paymentMethod)
                .deliveryMethod(deliveryMethod)
                .totalAmount(cartDetails.getTotalValue())
                .status(OrderStatus.COMPLETED)
                .thankYouMessage("Dziękujemy za złożenie zamówienia")
                .build();

        Order savedOrder = orderRepository.save(order);
        savedOrder.setId(savedOrder.getId());
        log.info("Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        log.info("Deleting order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Transactional
    public Order processOrder(Long orderId) {
        log.info("Processing order with ID: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        order.setStatus(OrderStatus.PROCESSING);
        log.info("Order processed successfully with ID: {}", orderId);
        return orderRepository.save(order);
    }
}
