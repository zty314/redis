package com.example.redis.controller;

import com.example.redis.dto.UserInfo;
import com.example.redis.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/9 10:54
 */
@RestController
public class UsersController {

    @Autowired(required = false)
    UserInfoMapper userInfoMapper;

    @Cacheable(value = "users", key = "#id", condition = "#id>0")
    @GetMapping("/users/{id}")
    public @ResponseBody
    Object getArticle(@PathVariable Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    @CachePut(value = "users", key = "#user.userId")
    @PutMapping("/users")
    public @ResponseBody
    Object putArticle(@RequestBody UserInfo user) {
        Integer isSuccess = userInfoMapper.updateByPrimaryKeySelective(user);
        return isSuccess == 1;
    }

    @CacheEvict(value = "users", key = "#id")
    @DeleteMapping("/users/{id}")
    public @ResponseBody
    Object removeArticle(@PathVariable Integer id) {
        Integer isSuccess = userInfoMapper.deleteByPrimaryKey(id);
        return isSuccess == 1;
    }
}