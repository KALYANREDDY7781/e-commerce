package com.myorg.product_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private  String apiPath;
    private String errorDescription;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;
}
