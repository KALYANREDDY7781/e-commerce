package com.myorg.Order.service;

import com.myorg.Order.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payments",url = "http://localhost:8083/payments")
public interface PaymentClient {

    @GetMapping("/{orderId}")
    PaymentDTO getPaymentByOrderId(@PathVariable int orderId);

    @PostMapping
    PaymentDTO processPayment(@RequestBody PaymentDTO paymentDTO);
}
