package com.smxknife.java2.thread.designpattern._10TwoPhaseTermination.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/26
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		// 未捕获异常处理器 和 退出钩子 测试
		System.out.println("main: Begin");

		// (1) 设置未捕获异常处理器
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("***********************");
						System.out.println("UncaughtExceptionHandler: Begin");
						System.out.println("CurrentThread = " + Thread.currentThread());
						System.out.println("thread = " + t);
						System.out.println("Exception = " + e);
						System.out.println("UncaughtExceptionHandler: End");
					}
				}
		);

		// (2) 设置退出钩子
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {

			System.out.println("********************");
			System.out.println("Shutdown hook: Begin");
			System.out.println("CurrentThread = " + Thread.currentThread());
			System.out.println("Shutdown hook: End");
		}, "shutdownHook-custom"));

		// (3) 大约3秒后执行整数除0
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " : Begin");
			System.out.println(Thread.currentThread().getName() + " : Sleep...");

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " : Divide");
			// 整数除0
			int x = 1 / 0;
			System.out.println(Thread.currentThread().getName() + " : End");
		}, "MyThread").start();

		System.out.println("main: End");
	}
}
