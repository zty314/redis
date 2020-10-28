package com.example.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 19:15
 */
@Data
@AllArgsConstructor
public class QueenMessage implements Serializable {

    private String title;

    private String content;

}