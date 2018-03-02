package com.smxknife.springboot.v2.ex03.listener;

import com.smxknife.springboot.v2.ex03.domain.User;
import com.smxknife.springboot.v2.ex03.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationRegisterListener {

    @EventListener
    public void register(UserRegisterEvent event) {
        User user = event.getUser();

        System.out.println("AnnotationRegisterListener " + user.getName() + ", " + user.getPassword());
    }
}
