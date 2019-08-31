package com.smxknife.java2.thread.scheduledexecutorservice.demo06;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
@AllArgsConstructor
public class MyRunnable06 implements Runnable {

	String name;

	@Override
	public void run() {
//		try {
//			while (true) {
//				if (Thread.currentThread().isInterrupted()) {
//					throw new InterruptedException();
//				}
//				System.out.println(Thread.currentThread().getName() + " run | name = " + name);
//				TimeUnit.SECONDS.sleep(1);
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println(Thread.currentThread().getName() + "中断了");
//		}

		// 采用这种方式虽然可以cancel，但是，任务会继续执行，所以想要取消任务，需要调用上面的方法
		// 采用Thread.currentThread().isInterrupt()来进行终止
		while (true) {
			System.out.println(Thread.currentThread().getName() + " run | name = " + name);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
