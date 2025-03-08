package com.myorg.Order.service;

import com.myorg.Order.dto.OrdersDto;
import com.myorg.Order.dto.PaymentDTO;
import com.myorg.Order.dto.ProductDto;
import com.myorg.Order.entity.Order;
import com.myorg.Order.exception.CustomerNotFoundException;
import com.myorg.Order.exception.OrderNotFoundException;
import com.myorg.Order.exception.OutOfStockException;
import com.myorg.Order.exception.ProductNotFoundException;
import com.myorg.Order.mapper.OrdersMapper;
import com.myorg.Order.repository.OrderItemRepository;
import com.myorg.Order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private PaymentClient paymentClient;
    private ProductClient productClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,PaymentClient paymentClient,
                            ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentClient = paymentClient;
        this.productClient = productClient;
    }

    @Override
    public OrdersDto placeOrder(OrdersDto ordersDto, PaymentDTO paymentDTO) {
        List<Integer> notFoundProducts = new ArrayList<>();
        notFoundProducts = ordersDto.getOrderItems().stream()
                .map(orderItemDto -> orderItemDto.getProductId())
                .collect(Collectors.toList());

        for(int p: notFoundProducts){
            ProductDto productDto = productClient.getProductById(p);
            if(productDto == null){
                throw new ProductNotFoundException("product not found for given id: "+p);
            }
            else{
                int quantity = productDto.getQuantity();
                quantity = 1;
                System.out.println("Quantity: "+quantity);
                if(quantity == 0){
                    throw new OutOfStockException("Given product is out of stock. Please check after sometime.");
                }
            }
        }
        Order order = OrdersMapper.mapToOrder(new Order(),ordersDto);
        order.setOrderDate(LocalDateTime.now());
        Order order1 = orderRepository.save(order);
        int orderId = order1.getOrderId();
        paymentDTO.setOrderId(orderId);
        paymentClient.processPayment(paymentDTO);
        return OrdersMapper.mapToOrdersDto(order);
    }

    @Override
    public OrdersDto getOrderById(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new OrderNotFoundException("Order not found with orderId: "+orderId);
        }
        return OrdersMapper.mapToOrdersDto(order.get());
    }

    @Override
    public List<OrdersDto> getAllOrdersByCustomerId(int customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if(orders.isEmpty()){
            throw new CustomerNotFoundException("No orders Found for given customer");
        }
        List<OrdersDto> ordersDtoList = orders.stream()
                .map(order -> OrdersMapper.mapToOrdersDto(order))
                .collect(Collectors.toList());
        return ordersDtoList;
    }

    @Override
    public OrdersDto cancelOrder(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new OrderNotFoundException("Order not found with orderId: "+orderId);
        }
        order.get().setOrderStatus("CANCELLED");
        Order order1 = orderRepository.save(order.get());
        return OrdersMapper.mapToOrdersDto(order1);
    }

    @Override
    public OrdersDto updateOrderStatus(int orderId, String status) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            order.get().setOrderStatus(status);
            Order order1 = orderRepository.save(order.get());
            return OrdersMapper.mapToOrdersDto(order1);
        }
        return null;
    }
}
