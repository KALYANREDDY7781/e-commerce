package com.myorg.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private int orderId;
    private LocalDateTime orderDate;
    private int customerId;
    private List<ProductDto> products;
    private double totalAmount;
    private PaymentDTO paymentDTO;


}
