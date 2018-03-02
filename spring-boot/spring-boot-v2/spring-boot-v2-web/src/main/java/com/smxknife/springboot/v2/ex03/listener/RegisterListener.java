package com.smxknife.springboot.v2.ex03.listener;

import com.smxknife.springboot.v2.ex03.domain.User;
import com.smxknife.springboot.v2.ex03.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent>{

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {

        User user = event.getUser();

        System.out.println("RegisterListener " + user.getName() + ", " + user.getPassword());
    }
}
