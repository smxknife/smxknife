package com.smxknife.springboot.v2.ex03.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
	@Override
	public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
		System.out.println("MyApplicationStartedListener...");
	}
}
