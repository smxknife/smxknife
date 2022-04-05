package com.smxknife.java2.thread.semaphore.demo01;

/**
 * @author smxknife
 * 2018-12-21
 */
public class ThreadA extends AbsThread {

	public ThreadA(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.testMethod();
	}
}
