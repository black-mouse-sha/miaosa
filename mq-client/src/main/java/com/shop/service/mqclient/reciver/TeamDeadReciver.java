package com.shop.service.mqclient.reciver;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.shop.service.module.service.TeamService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RabbitListener(queues = "teamTimeOutDeadQueue")
@Component
public class TeamDeadReciver {


    @Autowired
    private TeamService teamService;

    @RabbitHandler
    public void process(JSONObject j, Channel channel, Message message){
        System.out.println("触发死信队列");
        System.out.println(j);
        Long id = (Long)j.get("id");
        teamService.makeActivityTimeout(id);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {

        }
    }
}
