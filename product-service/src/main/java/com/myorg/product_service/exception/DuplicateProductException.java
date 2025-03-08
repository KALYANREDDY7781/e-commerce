package com.myorg.product_service.exception;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String msg){
        super(msg);
    }
}
