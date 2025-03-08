package com.myorg.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderEvent {

    private int customerId;
    private String status;
    private String msg;
}
