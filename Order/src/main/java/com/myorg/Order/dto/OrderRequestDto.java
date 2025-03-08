package com.myorg.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private OrdersDto ordersDto;
    private PaymentDTO paymentDTO;
}
