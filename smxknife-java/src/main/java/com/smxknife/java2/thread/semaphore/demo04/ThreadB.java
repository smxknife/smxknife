package com.smxknife.java2.thread.semaphore.demo04;

/**
 * @author smxknife
 * 2018-12-21
 */
public class ThreadB extends AbsThread {

	public ThreadB(Service service, boolean interrupt) {
		this.service = service;
		this.interrupt = interrupt;
	}
}
