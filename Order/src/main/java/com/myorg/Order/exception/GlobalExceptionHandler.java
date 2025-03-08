package com.myorg.Order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException
            (OrderNotFoundException exception, WebRequest webRequest){
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception,
                                                                      WebRequest webRequest){
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderStatusException(InvalidOrderStatusException exception,
                                                                      WebRequest webRequest){
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidateException(MethodArgumentNotValidException exception,
                                                                           WebRequest webRequest){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        HashMap<String,String> validationErrors = new HashMap<>();
        errorList.forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            validationErrors.put(fieldName,msg);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception,
                                                                      WebRequest webRequest){
        System.out.println("In Exception class");
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
    }
}
