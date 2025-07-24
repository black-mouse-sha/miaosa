package com.shop.service.mqclient.reciver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@RabbitListener(queues = "defaultQueue")
@Component
public class DefaultQueueReciver {

    /**
     * defaultQueue中存在未被消费的新消息时会触发该函数执行
     * @param msgStr 发送消息的实际内容，可以使用字符串可以使用JSONObject或者java对象，以发送的消息类型为准
     * @param message rabbitmq的消息参数，内部包含消息标识，消息头部信息，消息体等
     * @param channel rabbitmq的频道参数，用来做消息应答，一旦应答之后该消息就被认为已经被消费
     * @return
     */
    @RabbitHandler
    public void process(String msgStr, Message message, Channel channel){
        //输出发送的消息内容
        System.out.println(msgStr);

        try {
            //使用channel对象设置该消息已经被消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
