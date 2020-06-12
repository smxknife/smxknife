package com.smxknife.guava.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author smxknife
 * 2019/12/29
 */
public class GuavaEventTest {
	public static void main(String[] args) {
		EventBus bus1 = new EventBus();

		GuavaEvent guavaEvent = new GuavaEvent();

		bus1.register(guavaEvent);
		bus1.post("Tom");

		EventBus bus2 = new EventBus();
		bus2.register(guavaEvent);
		bus2.post("LL");
	}
}
