package com.smxknife.java2.shutdownhook;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/5/8
 */
public class _03_ShutdownHook_MockLongTime_Demo {
	public static void main(String[] args) throws InterruptedException {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("run shutdownHook");
		}));

		TimeUnit.HOURS.sleep(1);
		System.out.println("interrupted by signal 2: SIGINT，可以执行");
	}
}
