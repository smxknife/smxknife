package com.smxknife.java2.thread.thread._03yield;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/10/11
 */
public class _Run {
	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("MyGroup");
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(group, () -> {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " - yield");
					Thread.yield();
					System.out.println(Thread.currentThread().getName() + " activeCount: " + group.activeCount());
				}
			}, "th-" + i);
			executorService.execute(thread);
		}
	}
}
