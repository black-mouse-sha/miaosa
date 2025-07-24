package com.shop.service.mqclient.reciver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RabbitListener(queues = "testDeadQueue")
@Component
public class TestDeadQueueReciver {

    @RabbitHandler
    public void process(String msg, Message message, Channel channel){
        System.out.println("testDeadQueue死信队列消费者："+msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
