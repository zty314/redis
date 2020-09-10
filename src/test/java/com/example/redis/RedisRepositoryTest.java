package com.example.redis;

import com.example.redis.po.Address;
import com.example.redis.po.Person;
import com.example.redis.redis.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/9 10:16
 */
@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void test(){

        Person rand = new Person("zimug", "汉神");
        rand.setAddress(new Address("杭州", "中国"));
        personRepository.save(rand);  //存

        Optional<Person> op = personRepository.findById(rand.getId()); //取
        Person p2 = op.get();

        personRepository.count(); //统计Person的数量
        System.out.println("统计Person的数量"+personRepository.count());
        personRepository.delete(rand);  //删除person对象rand
    }

}