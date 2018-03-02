package com.smxknife.springboot.v2.ex03.service.impl;

import com.smxknife.springboot.v2.ex03.domain.User;
import com.smxknife.springboot.v2.ex03.event.UserRegisterEvent;
import com.smxknife.springboot.v2.ex03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void register(User user) {
        applicationContext.publishEvent(new UserRegisterEvent(this, user));
    }
}
