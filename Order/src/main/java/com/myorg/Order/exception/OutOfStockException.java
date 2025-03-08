package com.myorg.Order.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String s) {
        super(s);
    }
}
