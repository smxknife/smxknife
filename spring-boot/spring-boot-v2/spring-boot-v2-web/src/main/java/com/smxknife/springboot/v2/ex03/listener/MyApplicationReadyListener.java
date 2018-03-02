package com.smxknife.springboot.v2.ex03.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		System.out.println("MyApplicationReadyListener...");
	}
}
