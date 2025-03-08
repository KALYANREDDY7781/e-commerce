package com.myorg.notification_service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class NotificationConsumer {

    @Bean
    public Consumer<OrderEvent> orderConsumer(){
        System.out.println("In Notification service");
        return orderEvent -> {
            System.out.println("Notification received for customer id: "+ orderEvent.getCustomerId() +" : "
                    + orderEvent.getStatus());
        };
    }


}
