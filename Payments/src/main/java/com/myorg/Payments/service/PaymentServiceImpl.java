package com.myorg.Payments.service;

import com.myorg.Payments.dto.PaymentDTO;
import com.myorg.Payments.entity.Payment;
import com.myorg.Payments.exception.PaymentNotFoundException;
import com.myorg.Payments.mapper.PaymentMapper;
import com.myorg.Payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public PaymentDTO processPayment(PaymentDTO paymentDto) {
        Payment payment = new Payment();
        payment.setOrderId(paymentDto.getOrderId());
        payment.setTransactionId(generateTransactionID());
        payment.setPaymentMode(paymentDto.getPaymentMode());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return PaymentMapper.mapToPaymentDto(payment,new PaymentDTO());
    }

    @Override
    public PaymentDTO getPaymentByOrderId(int orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if(payment == null){
            throw new PaymentNotFoundException("Payment not found for Order ID: "+orderId);
        }
        return PaymentMapper.mapToPaymentDto(payment,new PaymentDTO());
    }

    private String generateTransactionID() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomString = UUID.randomUUID().toString().replace("-","").
                substring(0,6).toUpperCase();
        return "TXN-"+date+randomString;
    }


}
