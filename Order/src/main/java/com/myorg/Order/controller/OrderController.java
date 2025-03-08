package com.myorg.Order.controller;

import com.myorg.Order.dto.*;
import com.myorg.Order.service.InvoiceService;
import com.myorg.Order.service.OrderPublisher;
import com.myorg.Order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final InvoiceService invoiceService;
    private final OrderPublisher orderPublisher;

    @Autowired
    public OrderController(OrderService orderService,InvoiceService invoiceService, OrderPublisher orderPublisher) {
        this.orderService = orderService;
        this.invoiceService = invoiceService;
        this.orderPublisher = orderPublisher;
    }

    @GetMapping("/invoice/{orderId}")
    private ResponseEntity<InvoiceDTO> getInvoice(@PathVariable int orderId){
        InvoiceDTO invoiceDTO = invoiceService.generateInvoice(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDTO);
    }
    /*@PostMapping
    public ResponseEntity<OrdersDto> createOrder(@Valid @RequestBody OrdersDto ordersDto){
        OrdersDto placedOrder = orderService.placeOrder(ordersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(placedOrder);
    }*/

    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@Valid @RequestBody OrderRequestDto order){
        OrdersDto placedOrder = orderService.placeOrder(order.getOrdersDto(),order.getPaymentDTO());
        OrderEvent event = new OrderEvent(placedOrder.getCustomerId(),"PLACED","Order is placed");
        orderPublisher.sendOrderEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(placedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable int orderId){
        OrdersDto order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrdersDto>> getOrdersByCustomerId(@PathVariable int customerId){
        List<OrdersDto> order = orderService.getAllOrdersByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrdersDto> cancelOrder(@PathVariable int orderId){
        OrdersDto order = orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrdersDto> updateOrderStatus(@PathVariable int orderId,@RequestParam String status){
        OrdersDto order = orderService.updateOrderStatus(orderId,status);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
