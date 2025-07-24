package com.shop.service.mqclient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderQueueConfig {

    @Bean
    public Queue orderQueue(){
        return new Queue("orderQueue",true);
    }

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange("orderExchange",true,false);
    }

    @Bean
    Binding orderBinding(){
        return new Binding("orderQueue",
                Binding.DestinationType.QUEUE,
                "orderExchange",
                "orderRouter",null);
    }
}