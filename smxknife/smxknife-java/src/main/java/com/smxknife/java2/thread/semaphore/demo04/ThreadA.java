package com.smxknife.java2.thread.semaphore.demo04;


/**
 * @author smxknife
 * 2018-12-21
 */
public class ThreadA extends AbsThread {

	public ThreadA(Service service, boolean interrupt) {
		this.service = service;
		this.interrupt = interrupt;
	}
}
