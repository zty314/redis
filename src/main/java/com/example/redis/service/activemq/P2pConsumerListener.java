package com.example.redis.service.activemq;

import com.example.redis.dto.QueenMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 19:15
 */
@Component
@Slf4j
public class P2pConsumerListener {
    @JmsListener(destination = "message.queue")
    public void insertVisitLog(QueenMessage queenMessage) {
        log.info("消费者接收数据 : " + queenMessage);
    }
}