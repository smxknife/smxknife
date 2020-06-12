package com.smxknife.java2.thread.executorservice.demo03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/3/6
 */
public class _Run2_ThreadPoolMonitor {
	public static void main(String[] args) throws InterruptedException {
		_Run2_ThreadPoolMonitor run = new _Run2_ThreadPoolMonitor();
		TimeUnit.SECONDS.sleep(15); // 为了打开jvisualvm
		run.test();

	}

	private void test() {
		ExecutorService service = ThreadPoolMonitor.newFixedThreadPool(5, "test");
		for (int i = 0; i < 100; i++) {
			service.execute(this::cycle);
		}
	}

	private void cycle() {
		System.out.println("---- " + Thread.currentThread().getName());
		new Thread(() -> {
			System.out.println("**** " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "inner-thread").run();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
