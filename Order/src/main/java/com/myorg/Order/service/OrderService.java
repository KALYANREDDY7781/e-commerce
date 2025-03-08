package com.myorg.Order.service;

import com.myorg.Order.dto.OrdersDto;
import com.myorg.Order.dto.PaymentDTO;

import java.util.List;

public interface OrderService {

    OrdersDto placeOrder(OrdersDto ordersDto, PaymentDTO paymentDTO);

    OrdersDto getOrderById(int orderId);

    List<OrdersDto> getAllOrdersByCustomerId(int customerId);

    OrdersDto cancelOrder(int orderId);

    OrdersDto updateOrderStatus(int orderId, String status);


}
