package com.example.redis.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 19:10
 */
@EnableJms //开启消息中间件的服务能力
@Configuration
public class CostomActiveMQConfig {

    //配置一个消息队列（P2P模式）
    @Bean
    public Queue messageQueue() {
        //这里相当于为消息队列起一个名字用于生产消费用户访问日志
        return new ActiveMQQueue("message.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("message.topic");
    }
}