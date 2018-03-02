package com.smxknife.springboot.v2.ex03.event;

import com.smxknife.springboot.v2.ex03.domain.User;
import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
