package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CartDetails {
    private Long id;
    private List<OrderItem> productIds;
    private String status;
    private LocalDateTime creationTime;
    private BigDecimal totalValue;
}
