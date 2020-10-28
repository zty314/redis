package com.example.redis.controller.activemq;

import com.example.redis.dto.QueenMessage;
import javax.jms.Queue;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 19:16
 */
@RestController
public class P2pProducerController {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue messageQueue;

    @RequestMapping("/send")
    public QueenMessage send() {
        QueenMessage queenMessage = new QueenMessage("测试", "测试内容");

        jmsMessagingTemplate.convertAndSend(messageQueue, queenMessage);
        return queenMessage;
    }
}