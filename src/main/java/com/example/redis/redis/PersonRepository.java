package com.example.redis.redis;

import com.example.redis.po.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/9 10:16
 */
//泛型第二个参数是id的数据类型
public interface PersonRepository extends CrudRepository<Person, String> {
    // 继承CrudRepository，获取基本的CRUD操作
}