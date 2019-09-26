package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ThreadInterruptBlockedEnable implements Runnable {
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) { // 这样处理，可以让线程中断后无法继续执行
			System.out.println(Thread.currentThread().getName() + " | is running" );
			try {
				TimeUnit.SECONDS.sleep(3); // 线程会阻塞在这里
			} catch (InterruptedException e) {
				System.out.println("在阻塞处接收到中断请求");
				e.printStackTrace();
				break; // 通过主动break，让线程终止
			}
		}
	}
}
