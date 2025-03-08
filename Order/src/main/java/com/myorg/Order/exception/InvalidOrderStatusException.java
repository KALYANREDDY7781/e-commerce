package com.myorg.Order.exception;

public class InvalidOrderStatusException extends RuntimeException{

    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
