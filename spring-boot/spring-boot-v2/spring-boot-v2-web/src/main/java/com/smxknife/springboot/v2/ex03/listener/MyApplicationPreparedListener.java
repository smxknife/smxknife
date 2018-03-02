package com.smxknife.springboot.v2.ex03.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {
	@Override
	public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
		System.out.println("MyApplicationPreparedListener...");
	}
}
