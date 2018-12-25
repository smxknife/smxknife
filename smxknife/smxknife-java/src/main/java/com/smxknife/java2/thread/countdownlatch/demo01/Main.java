package com.smxknife.java2.thread.countdownlatch.demo01;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();
		MyThread myThread = new MyThread(myService);
		myThread.start();
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName());
		myService.downM();
	}
}
