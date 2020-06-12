package com.smxknife.java2.thread.interrupt;

/**
 * 因为该方法会死循环，所以执行时需要慎重
 * @author smxknife
 * 2020/6/4
 */
@Deprecated
public class _Run_interrupt_threadInterrupted {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			int i = 0;
			while (!Thread.currentThread().isInterrupted()) {

				// Thread.interrupted()是用来清除Interrupt标记的，所以调用该方法不会让线程停止
				System.out.println(Thread.interrupted());
				System.out.println(Thread.currentThread().isInterrupted());

				System.out.println("i = " + i);
				if (i++ > 50) {
					Thread.currentThread().interrupt();
					System.out.println(Thread.interrupted());
					Thread.currentThread().interrupt();

				}
			}
		});
		thread.start();
	}
}
