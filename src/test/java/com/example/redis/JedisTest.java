package com.example.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/3 15:44
 */
public class JedisTest {
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("47.102.134.66", 6379)) {
            jedis.auth("123456");
            System.out.println(jedis.ping());
//            jedis.lpush("hello", "wy","hhhhh","licon","jsp");
            List<String> list = jedis.lrange("hello",0,-1);
            list.forEach(s -> System.out.println("value:"+s));
        }
    }
}