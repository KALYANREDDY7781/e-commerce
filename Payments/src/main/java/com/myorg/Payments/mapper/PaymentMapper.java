package com.myorg.Payments.mapper;

import com.myorg.Payments.dto.PaymentDTO;
import com.myorg.Payments.entity.Payment;

public class PaymentMapper {

    public static PaymentDTO mapToPaymentDto(Payment payment,PaymentDTO paymentDTO){
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentDTO.setPaymentMode(payment.getPaymentMode());
        paymentDTO.setPaymentTime(payment.getPaymentTime());
        paymentDTO.setTransactionId(payment.getTransactionId());
        return paymentDTO;
    }

    public static Payment mapToPayment(Payment payment,PaymentDTO paymentDTO){
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        payment.setPaymentTime(paymentDTO.getPaymentTime());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        return payment;
    }

}
