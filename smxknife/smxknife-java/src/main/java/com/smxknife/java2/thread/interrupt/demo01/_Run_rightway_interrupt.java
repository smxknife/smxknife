package com.smxknife.java2.thread.interrupt.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/25
 */
public class _Run_rightway_interrupt {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			boolean runFlag = true;
			while (runFlag) { // 正确的中断方式是，使用状态开关
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("interrupt");
					runFlag = false; // 在interrupt中关闭状态开关
				} else {
					System.out.println("normal");
				}
			}
		});

		thread.start();
		TimeUnit.SECONDS.sleep(2);
		thread.interrupt();
	}
}
