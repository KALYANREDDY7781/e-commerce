package com.myorg.Payments.service;

import com.myorg.Payments.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO processPayment(PaymentDTO paymentDto);

    PaymentDTO getPaymentByOrderId(int orderId);
}
