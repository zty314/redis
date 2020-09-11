package com.example.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//配置启用Redis的httpSession
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30 * 60 * 1000)
//开启缓存
@EnableCaching
//扫描mapper包的路径
@MapperScan(basePackages = {"com.example.redis.mapper"})
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
