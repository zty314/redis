package com.example.redis;

import com.example.redis.po.Address;
import com.example.redis.po.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/2 14:52
 */
@SpringBootTest
public class RedisConfigTest {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;   //以String序列化方式保存数据的通用模板类

    @Autowired
    private RedisTemplate redisTemplate;   //默认以JDK二进制方式保存数据的通用模板类

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;   //以redis string类型存取Java Object(序列化反序列化)

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations; //以redis的hash类型存储java Object

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations; //以redis的list类型存储java Object

    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOperations;   //以redis的set类型存储java Object

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOperations;  //以redis的zset类型存储java Object

    @Test
    public void testValueObj() throws Exception {
        Person person = new Person("boke", "byrant");
        person.setAddress(new Address("南京", "中国"));

        valueOperations.set("player:1", person, 20, TimeUnit.SECONDS); //20秒之后数据消失

        Person getBack = (Person) valueOperations.get("player:1");
        System.out.println(getBack);
    }

    @Test
    public void testSetOperation() throws Exception {
        Person person = new Person("boke", "byrant");
        Person person2 = new Person("curry", "stephen");

        setOperations.add("playerset", person, person2);  //向Set中添加数据项

        Set<Object> result = setOperations.members("playerset");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception {
        Person person = new Person("boke", "byrant");
        //使用hash的方法存储对象数据（一个属性一个属性的存，下节教大家简单的方法）
        hashOperations.put("hash:player", "firstname", person.getFirstname());
        hashOperations.put("hash:player", "lastname", person.getLastname());
        hashOperations.put("hash:player", "address", person.getAddress());
        System.out.println(hashOperations.get("hash:player", "firstname"));
    }

    @Test
    public void ListOperations() throws Exception {
        //将数据对象放入队列
        listOperations.leftPush("list:player", new Person("boke", "byrant"));
        listOperations.leftPush("list:player", new Person("Jordan", "Mikel"));
        listOperations.leftPush("list:player", new Person("curry", "stephen"));

        System.out.println(listOperations.leftPop("list:player"));
    }


    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> jacksonHashOperations;

    private HashMapper<Object, String, Object> jackson2HashMapper = new Jackson2HashMapper(false);

    @Test
    public void testHashPutAll() {

        Person person = new Person("boke", "byrant");
        person.setId("1");
        person.setAddress(new Address("南京", "中国"));

        //将对象以hash的形式放入数据库
        Map<String, Object> mappedHash = jackson2HashMapper.toHash(person);
        System.out.println(mappedHash);
        jacksonHashOperations.putAll("player" + person.getId(), mappedHash);

        //将对象从数据库取出来
        Map<String, Object> loadedHash = jacksonHashOperations.entries("player" + person.getId());
        Object map = jackson2HashMapper.fromHash(loadedHash);
        Person getback = new ObjectMapper().convertValue(map, Person.class);
        System.out.println(getback);
        System.out.println(person);
        //验证放进去的和取出来的数据一致
        Assert.isTrue(person.getFirstname().equals(getback.getFirstname()), "不相同！");
    }
}
