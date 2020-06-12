package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class _Run_interrupt_unblocked_enabled {
	public static void main(String[] args) throws InterruptedException {

		// Java中断机制是一种协作机制，也就是说通过中断并不能直接终止另一个线程，而需要被中断的线程自己处理中断

		//  ThreadInterruptUnblockedEnabled这种不阻塞的线程可以直接这样处理
		Thread thread = Executors.defaultThreadFactory().newThread(new ThreadInterruptUnblockedEnabled());

		thread.start();
		TimeUnit.SECONDS.sleep(2);
		thread.interrupt();
	}
}
