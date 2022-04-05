package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class _Run_interrupt_disabled {
	public static void main(String[] args) throws InterruptedException {

		// Java中断机制是一种协作机制，也就是说通过中断并不能直接终止另一个线程，而需要被中断的线程自己处理中断
		// 通过调用interrupt方法是无法中断线程的，下面的线程会一直输出
		Thread thread = Executors.defaultThreadFactory().newThread(new ThreadInterruptDisabled());

		thread.start();
		TimeUnit.SECONDS.sleep(10);
		thread.interrupt();
	}
}
