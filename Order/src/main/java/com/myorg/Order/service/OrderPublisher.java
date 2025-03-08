package com.myorg.Order.service;

import com.myorg.Order.dto.OrderEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private final StreamBridge streamBridge;


    public OrderPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishOrderEvent(OrderEvent orderEvent){
        streamBridge.send("orderCreated-out-0",orderEvent);
        System.out.println("Order event sent: "+orderEvent.getCustomerId());
    }
}
