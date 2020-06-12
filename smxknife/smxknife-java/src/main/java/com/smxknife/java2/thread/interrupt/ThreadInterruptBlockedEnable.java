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
				// 方式一：
				//break; // 通过主动break，让线程终止
				// 方式二：
				Thread.currentThread().interrupt();
				// 再次调用Interrupt，为什么会出现这种情况，因为阻塞方法在收到Interrupt之后会解除阻塞，然后再次调用Interrupt会将状态消除
				// 所以这里再次调用就可以正确的获取状态
			}
		}
	}
}
