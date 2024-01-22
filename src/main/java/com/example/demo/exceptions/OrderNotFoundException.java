package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends GeneralExceptionConfig {
    public OrderNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
