package com.smxknife.java2.shutdownhook;

/**
 * @author smxknife
 * 2020/5/8
 */
public class _01_ShutdownHookDemo {
	public static void main(String[] args) throws InterruptedException {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("run shutdownHook");
		}));
		System.out.println("程序正常执行完，可以触发");
	}
}
