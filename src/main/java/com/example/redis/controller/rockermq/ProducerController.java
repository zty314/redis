package com.example.redis.controller.rockermq;

import com.example.redis.config.RocketMQContants;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/28 22:20
 */
@RestController
public class ProducerController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/rmqsend")
    public String send(String msg) {
        rocketMQTemplate.convertAndSend(RocketMQContants.TEST_TOPIC,msg);
        return "success";
    }
}