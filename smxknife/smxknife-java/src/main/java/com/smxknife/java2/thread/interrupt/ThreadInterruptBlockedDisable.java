package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ThreadInterruptBlockedDisable implements Runnable {
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) { // 这样处理，可以让线程中断后无法继续执行

			/**
			 * 通过下面三组输出可以发现，调用interrupt之后，并没有改变当前线程的状态，所以也可以理解了为什么while的循环条件可以一直为真
			 */
			System.out.println("通过下面三组输出可以发现，调用interrupt之后，并没有改变当前线程的状态，所以也可以理解了为什么while的循环条件可以一直为真");
			System.out.println("isInterrupted ? " + Thread.currentThread().isInterrupted());
			System.out.println("state: " + Thread.currentThread().getState().name());
			System.out.println("isActive ? " + Thread.currentThread().isAlive());

			System.out.println(Thread.currentThread().getName() + " | is running" );
			try {
				TimeUnit.SECONDS.sleep(3); // 线程会阻塞在这里
			} catch (InterruptedException e) {
				System.out.println("在阻塞处接收到中断请求");
				e.printStackTrace();
			}
		}
	}
}
