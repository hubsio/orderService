package com.example.demo.client;

import com.example.demo.model.CartDetails;
import com.example.demo.model.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "cart-service", url = "http://localhost:8080")
public interface CartServiceClient {
//    @GetMapping("/cart/{cartId}")
//    void getCartId(@PathVariable Long cartId);

    @GetMapping("/products/{productId}")
    CartDetails getProductInfo(@PathVariable("productId") Long id);

    @GetMapping("/cart/{cartId}")
    CartDetails getCartDetails(@PathVariable Long cartId);
}

