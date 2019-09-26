package com.smxknife.java2.thread.interrupt;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ThreadInterruptUnblockedEnabled implements Runnable {
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) { // 这样处理，可以让线程中断后无法继续执行
			System.out.println(Thread.currentThread().getName() + " | is running" );
		}
	}
}
