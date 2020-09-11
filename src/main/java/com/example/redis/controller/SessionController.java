package com.example.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/11 16:14
 */
@RestController
public class SessionController {

    /**
     * 执行完这段之后，在redis里面可以查到当前用户的sessionID
     * 亲测有效
     * @param session
     * @return
     */
    @RequestMapping(value="/uid",method = RequestMethod.GET)
    public @ResponseBody
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}