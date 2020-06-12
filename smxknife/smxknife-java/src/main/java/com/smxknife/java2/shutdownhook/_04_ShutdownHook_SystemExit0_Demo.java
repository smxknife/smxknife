package com.smxknife.java2.shutdownhook;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/5/8
 */
public class _04_ShutdownHook_SystemExit0_Demo {
	public static void main(String[] args) throws InterruptedException {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("run shutdownHook");
		}));

		int i = 0;
		while (true) {
			if (i == 5) {
				System.exit(0);
			}
			i++;
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
