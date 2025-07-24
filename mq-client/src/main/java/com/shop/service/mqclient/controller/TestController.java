package com.shop.service.mqclient.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/msg")
    public String testMessage(@RequestParam(value="msg")String msg){
        //对defaultRouter这个路由发送消息，消息就会自动进入这个路由关联的交换机并分配到交换机的队列中也就是defaultQueue
        rabbitTemplate.convertAndSend("defaultExchange","defaultRouter",msg);
        return msg;
    }

    @GetMapping("/msg1")
    public String testMessage1(@RequestParam(value="msg")String msg){
        //对defaultRouter这个路由发送消息，消息就会自动进入这个路由关联的交换机并分配到交换机的队列中也就是defaultQueue
        rabbitTemplate.convertAndSend("testExchange","testRouter",msg,message -> {
            //设置当前消息队列找不到消费者可容忍时间为5秒，testExchange的消费者不存在的话消息就会进入testDeadQueue队列
            message.getMessageProperties().setExpiration("5000");
            return message;
        });
        return msg;
    }
}
