package com.myorg.Payments.repository;

import com.myorg.Payments.dto.PaymentDTO;
import com.myorg.Payments.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Payment findByOrderId(int orderId);
}
