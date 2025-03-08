package com.myorg.Payments.controller;

import com.myorg.Payments.dto.PaymentDTO;
import com.myorg.Payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO){
        PaymentDTO paymentDTO1 = paymentService.processPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO1);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable int orderId){
        PaymentDTO paymentDTO1 = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO1);
    }
}
