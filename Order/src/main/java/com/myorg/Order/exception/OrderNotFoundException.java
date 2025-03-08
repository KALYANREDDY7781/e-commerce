package com.myorg.Order.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String msg){
        super(msg);
    }
}
