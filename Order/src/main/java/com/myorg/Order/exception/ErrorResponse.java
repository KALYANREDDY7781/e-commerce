package com.myorg.Order.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private String apiPath;
    private String errorDescription;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;

}
