package com.example.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.function.BiConsumer;

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

    static class In1{}
    static class In2{}

    public static class MethodConversion {
        static void accept(In1 i1, In2 i2) {
            System.out.println("accept()");
        }
        static void someOtherName(In1 i1, In2 i2) {
            System.out.println("someOtherName()");
        }
        public static void main(String[] args) {
            BiConsumer<In1,In2> bic;

            bic = MethodConversion::accept;
            bic.accept(new In1(), new In2());

            bic = MethodConversion::someOtherName;
            // bic.someOtherName(new In1(), new In2()); // Nope
            bic.accept(new In1(), new In2());
        }
    }
}