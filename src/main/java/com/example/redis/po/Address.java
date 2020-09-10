package com.example.redis.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/2 14:53
 */
@Data
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = -8985545025228238771L;

    String city;
    String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }
}