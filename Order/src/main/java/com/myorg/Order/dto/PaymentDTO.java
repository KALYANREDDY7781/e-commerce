package com.myorg.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private int orderId;

    private String transactionId;

    private String paymentMode;

    private String paymentStatus;

    private LocalDateTime paymentTime;
}
