package com.myorg.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException
            (ProductNotFoundException exception, WebRequest webRequest){
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateProductException
            (DuplicateProductException exception, WebRequest webRequest){
        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());

        return new ResponseEntity<>(e,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException
            (MethodArgumentNotValidException exception, WebRequest webRequest){
        List<ObjectError> validationErrorList = exception.getBindingResult().getAllErrors();
        HashMap<String,String> validationErrors = new HashMap<>();

        validationErrorList.forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            validationErrors.put(fieldName,msg);
        });

       /* ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());*/

        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException
            (Exception exception, WebRequest webRequest){

        ErrorResponse e = new ErrorResponse(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
