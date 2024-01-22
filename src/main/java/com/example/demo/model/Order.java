package com.example.demo.model;

import com.example.demo.enumy.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cartId;
    private String address;
    private String firstName;
    private String lastName;
    private String paymentMethod;
    private String deliveryMethod;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private String thankYouMessage;
}
