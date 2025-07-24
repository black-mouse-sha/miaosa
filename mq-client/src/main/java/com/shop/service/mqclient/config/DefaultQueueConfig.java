package com.shop.service.mqclient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultQueueConfig {

    @Bean
    public Queue defaultQueue(){
        return new Queue("defaultQueue",true);
    }

    @Bean
    public DirectExchange defaultQueueExchange(){
        return new DirectExchange("defaultExchange",true,false,null);
    }

    @Bean
    public Binding defaultBinding(){
        return new Binding("defaultQueue",
                Binding.DestinationType.QUEUE,
                "defaultExchange",
                "defaultRouter",null);
    }
}
