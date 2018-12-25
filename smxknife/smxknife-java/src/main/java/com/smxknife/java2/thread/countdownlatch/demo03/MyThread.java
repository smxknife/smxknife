package com.smxknife.java2.thread.countdownlatch.demo03;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyThread extends Thread {

	private MyService myService;

	public MyThread(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.prepare();
	}
}
