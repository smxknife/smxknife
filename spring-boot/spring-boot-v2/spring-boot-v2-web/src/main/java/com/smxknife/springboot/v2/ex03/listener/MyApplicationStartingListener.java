package com.smxknife.springboot.v2.ex03.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationStartingListener implements ApplicationListener<ApplicationStartingEvent> {
	@Override
	public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
		System.out.println("MyApplicationStartingListener...");
	}
}
