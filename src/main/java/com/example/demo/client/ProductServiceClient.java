package com.example.demo.client;

import com.example.demo.model.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceClient {
    @GetMapping("/products/{productId}")
    Optional<OrderItem> getProductInfo(@PathVariable("productId") Long id);
}
