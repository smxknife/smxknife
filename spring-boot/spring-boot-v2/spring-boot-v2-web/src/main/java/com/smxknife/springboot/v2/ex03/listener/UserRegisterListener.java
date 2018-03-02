package com.smxknife.springboot.v2.ex03.listener;

import com.smxknife.springboot.v2.ex03.domain.User;
import com.smxknife.springboot.v2.ex03.event.UserRegisterEvent;
import com.smxknife.springboot.v2.ex03.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterListener implements SmartApplicationListener {

    /**
     * 该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param eventType
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return UserRegisterEvent.class.isAssignableFrom(eventType);
    }

    /**
     * 该方法返回true&supportsEventType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(@Nullable Class<?> sourceType) {
        return UserService.class.isAssignableFrom(sourceType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
        User user = userRegisterEvent.getUser();
        System.out.println("UserRegisterListener " + user.getName() + ", " + user.getPassword());
    }

    /**
     * 同步情况下监听执行的顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 3;
    }
}
