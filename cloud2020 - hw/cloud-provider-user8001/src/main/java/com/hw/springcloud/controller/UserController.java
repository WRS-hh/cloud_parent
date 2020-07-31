package com.hw.springcloud.controller;


import com.hw.springcloud.dao.UserDao;
import com.hw.springcloud.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class UserController {


    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserDao userDao;


    @GetMapping(value = "/user/zhuce")
    public String zhuce(String username, String password) {
        if (username == null) {
            return "用户名不能为空";
        }
        if (password == null) {
            return "密码不能为空";
        }
        //查询是否username重复
        if (userDao.count(username) > 0) {
            return "用户名重复";
        }

        userDao.inseruser(username, password);
        return "注册成功";
    }

    @GetMapping(value = "/user/denglu")
    public String denglu(String username, String password, HttpSession session) {
        if (username == null) {
            return "用户名不能为空";
        }
        if (password == null) {
            return "密码不能为空";
        }


        User user = userDao.selectuser(username, password);
        if (user == null) {
            return "用户名或密码错误";
        }
        session.setAttribute("user", user);
        return "登录成功";
    }


}
