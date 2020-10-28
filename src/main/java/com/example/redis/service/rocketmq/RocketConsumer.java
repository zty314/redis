package com.example.redis.service.rocketmq;

import com.example.redis.config.RocketMQContants;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/28 22:21
 *
 * 同一个消费者组(consumerGroup)的消费者订阅同一话题(topic)，组内只能消费一次消息。
 * 不同消费者组(consumerGroup)订阅同一话题(topic)，组内只消费一次，但多组消费多次。
 */
@Service
@RocketMQMessageListener(consumerGroup = RocketMQContants.CONSUMER_GROUP1, topic = RocketMQContants.TEST_TOPIC)
public class RocketConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.err.println("接收到消息：" + message);
    }
}