package com.smxknife.guava.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @author smxknife
 * 2019/12/29
 */
public class GuavaEvent {

	@Subscribe
	public void subscribe(String str) {
		System.out.println("执行subscribe方法，传入的参数：" + str);
	}
}
