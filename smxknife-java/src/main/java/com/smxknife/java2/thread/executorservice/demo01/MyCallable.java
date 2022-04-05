package com.smxknife.java2.thread.executorservice.demo01;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019/8/28
 */
@AllArgsConstructor
public class MyCallable implements Callable<UserInfo> {

	private UserInfo userInfo;

	@Override
	public UserInfo call() throws Exception {
		userInfo.setUsername(Thread.currentThread().getName());
		userInfo.setPassword("123456");

		Random random = new Random();

		label:
		while (true) {
			System.out.println(Thread.currentThread().getName() + " running...");
			if (Thread.currentThread().isInterrupted() == true) {
				System.out.println(Thread.currentThread().getName() + " interrupted!!!");
				break label;
			}
			if (random.nextInt() % 5 == 0) {
				break label;
			}
		}
		System.out.println(Thread.currentThread().getName() + " finish...");
		return userInfo;
	}
}
