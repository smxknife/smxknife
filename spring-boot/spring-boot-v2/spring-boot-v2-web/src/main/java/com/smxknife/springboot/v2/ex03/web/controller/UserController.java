package com.smxknife.springboot.v2.ex03.web.controller;

import com.smxknife.springboot.v2.ex03.domain.User;
import com.smxknife.springboot.v2.ex03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    public String register(@RequestBody User user) {
        //调用注册业务逻辑
        userService.register(user);
        return "注册成功.";
    }
}
