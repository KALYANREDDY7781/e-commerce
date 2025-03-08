package com.myorg.Order.mapper;

import com.myorg.Order.dto.OrderItemDto;
import com.myorg.Order.dto.OrdersDto;
import com.myorg.Order.entity.Order;
import com.myorg.Order.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {

    //Map OrdersDto to Order
    public static Order mapToOrder(Order order,OrdersDto ordersDto){
        order.setCustomerId(ordersDto.getCustomerId());
        order.setTotalAmount(ordersDto.getTotalAmount());
        order.setOrderDate(ordersDto.getOrderDate());
        order.setOrderStatus(ordersDto.getOrderStatus());

        List<OrderItem> orderItems = ordersDto.getOrderItems().stream()
                .map(orderItemDto -> new OrderItem(orderItemDto.getItemId(),
                        orderItemDto.getProductId(),
                        orderItemDto.getQuantity(),
                        orderItemDto.getPrice(),
                        order
                )).collect(Collectors.toList());
        order.setOrderItems(orderItems);

        return order;
    }

    //Map OrderItemDto to OrderItem
    public static OrderItem mapToOrderItem(OrderItemDto orderItemDto,Order order){
        OrderItem orderItem = new OrderItem();

        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setOrder(order);

        return orderItem;
    }

    public static OrdersDto mapToOrdersDto(Order order){
        OrdersDto ordersDto = new OrdersDto();

        ordersDto.setCustomerId(order.getCustomerId());
        ordersDto.setOrderDate(order.getOrderDate());
        ordersDto.setOrderStatus(order.getOrderStatus());
        ordersDto.setTotalAmount(order.getTotalAmount());

        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                .map(orderItem -> mapToOrderItemDto(orderItem))
                .collect(Collectors.toList());

        ordersDto.setOrderItems(orderItemDtos);
        return ordersDto;
    }

    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItemId(orderItem.getOrderItemId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }
}
