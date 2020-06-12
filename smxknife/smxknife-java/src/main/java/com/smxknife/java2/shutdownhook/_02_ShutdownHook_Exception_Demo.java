package com.smxknife.java2.shutdownhook;

/**
 * @author smxknife
 * 2020/5/8
 */
public class _02_ShutdownHook_Exception_Demo {
	public static void main(String[] args) throws InterruptedException {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("run shutdownHook");
		}));
		int i = 1 / 0;
		System.out.println("程序异常退出，可以触发");
	}
}
