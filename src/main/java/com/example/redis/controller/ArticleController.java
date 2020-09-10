package com.example.redis.controller;

import com.example.redis.mapper.UserInfoMapper;
import com.example.redis.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/9 10:54
 */
@RestController
public class ArticleController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Cacheable(value="article")
    @GetMapping( "/article/{id}")
    public @ResponseBody Object getArticle(@PathVariable Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }
}