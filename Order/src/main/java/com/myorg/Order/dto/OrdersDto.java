package com.myorg.Order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

    private int customerId;  // ID of the customer placing the order
    private List<OrderItemDto> orderItems;  // List of items in the order
    @Min(value = 1,message = "Amount should not be less than 1")
    private double totalAmount;  // Total cost of the order
    @Pattern(regexp = "PENDING|CONFIRMED|SHIPPED|DELIVERED|CANCELLED",
            message = "Invalid order status. Allowed values: 'PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED'")
    private String orderStatus;  // Status of the order (e.g: PENDING, SHIPPED)
    private LocalDateTime orderDate; // Date when the order was placed
}
