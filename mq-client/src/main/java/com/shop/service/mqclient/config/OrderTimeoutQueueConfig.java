package com.shop.service.mqclient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class OrderTimeoutQueueConfig {

    @Bean
    public Queue orderTimeoutDeadQueue(){
        return new Queue("orderTimeoutDeadQueue",true);
    }
    @Bean
    public DirectExchange orderTimeoutDeadExchange(){
        return new DirectExchange("orderTimeoutDeadExchange",true,false);
    }
    @Bean
    Binding orderTimeoutDeadBinding(){
        return new Binding("orderTimeoutDeadQueue",
                Binding.DestinationType.QUEUE,
                "orderTimeoutDeadExchange",
                "orderTimeoutDeadRouter",null);
    }

    @Bean
    public Queue orderTimeoutQueue(){
        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange", "orderTimeoutDeadExchange");
        arguments.put("x-dead-letter-routing-key", "orderTimeoutDeadRouter");
        return new Queue("orderTimeoutQueue",true,false,false,arguments);
    }

    @Bean
    public DirectExchange orderTimeoutExchange(){
        return new DirectExchange("orderTimeoutExchange",true,false);
    }
    @Bean
    Binding orderTimeoutBinding(){
        return new Binding("orderTimeoutQueue",
                Binding.DestinationType.QUEUE,
                "orderTimeoutExchange",
                "orderTimeoutRouter",null);
    }
}

