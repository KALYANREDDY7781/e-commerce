package com.myorg.notification_service;

import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class NotificationConsumer {

    public Consumer<OrderEvent> orderConsumer(){
        return orderEvent -> {
            System.out.println("Notification received for customer id: "+ orderEvent.getCustomerId() +" : "
                    + orderEvent.getStatus());
        };
    }


}
