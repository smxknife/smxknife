package com.smxknife.java2.thread.semaphore.demo07;

/**
 * @author smxknife
 * 2018-12-22
 */
public class ThreadP extends AbsThread {

	public ThreadP(RepastService service) {
		super(service);
	}

	@Override
	public void exe() {
		service.set();
	}
}
