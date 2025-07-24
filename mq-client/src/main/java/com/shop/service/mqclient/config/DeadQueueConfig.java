package com.shop.service.mqclient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadQueueConfig {

    @Bean
    public Queue testDeadQueue(){
        return new Queue("testDeadQueue",true);
    }

    @Bean
    DirectExchange testDeadExchange(){
        return new DirectExchange("testDeadExchange",true,false);
    }

    @Bean
    Binding testDeadBinding(){
        return new Binding("testDeadQueue",
                Binding.DestinationType.QUEUE,
                "testDeadExchange",
                "testDeadRouter",null);
    }

    @Bean
    public Queue testQueue(){
        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange", "testDeadExchange");
        arguments.put("x-dead-letter-routing-key", "testDeadRouter");
        return new Queue("testQueue", true, false, false, arguments);
    }

    @Bean
    DirectExchange testExchange(){
        return new DirectExchange("testExchange",true,false);
    }
    @Bean
    Binding testBinding(){
        return new Binding("testQueue",
                Binding.DestinationType.QUEUE,
                "testExchange",
                "testRouter",null);
    }
}
