package com.example.redis.service.activemq;

import com.example.redis.dto.QueenMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 20:06
 */
@Component
public class TopicConsumer {
    @JmsListener(destination = "message.topic")
    public void receiver1(QueenMessage queenMessage) {
        System.out.println("TopicConsumer : receiver1 : " + queenMessage);
    }

    @JmsListener(destination = "message.topic")
    public void receiver2(QueenMessage queenMessage) {
        System.out.println("TopicConsumer : receiver2 : " + queenMessage);
    }

    @JmsListener(destination = "message.topic")
    public void receiver3(QueenMessage queenMessage) {
        System.out.println("TopicConsumer : receiver3 : " + queenMessage);
    }
}