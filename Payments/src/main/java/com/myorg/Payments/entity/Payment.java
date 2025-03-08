package com.myorg.Payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "transaction_id",unique = true)
    private String transactionId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;


}
