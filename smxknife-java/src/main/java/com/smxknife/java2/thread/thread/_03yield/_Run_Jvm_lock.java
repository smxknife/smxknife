package com.smxknife.java2.thread.thread._03yield;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_Jvm_lock {
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		new Thread(() -> {
			while (true) {
				synchronized (lock) {
					System.out.println(Thread.currentThread().getName());
					for (int i = 0; i < 100000000; i++) {
						Math.random();
					}
					Thread.yield();
				}
			}
		}, "yield_jvm_view_1").start();

		new Thread(() -> {
			while (true) {
				synchronized (lock) {
					System.out.println(Thread.currentThread().getName());
					for (int i = 0; i < 100000000; i++) {
						Math.random();
					}
					Thread.yield();
				}
			}
		}, "yield_jvm_view_2").start();

	}
}
