package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class _Run_interrupt_blocked_disable {
	public static void main(String[] args) throws InterruptedException {

		// Java中断机制是一种协作机制，也就是说通过中断并不能直接终止另一个线程，而需要被中断的线程自己处理中断

		//  ThreadInterruptBlockedDisable,会在线程内部阻塞，如果在阻塞处发生interrupt会无法停止线程
		Thread thread = Executors.defaultThreadFactory().newThread(new ThreadInterruptBlockedDisable());

		thread.start();
		TimeUnit.SECONDS.sleep(10);
		thread.interrupt();

	}
}
