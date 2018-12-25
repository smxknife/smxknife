package com.smxknife.java2.thread.countdownlatch.demo03;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();
		MyThread[] threads = new MyThread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new MyThread(myService);
			threads[i].setName("runner-" + i);
			threads[i].start();
		}

		Thread.sleep(2000); // 模拟准备时间
		myService.cmdStart();
	}
}
