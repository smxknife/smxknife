package com.smxknife.java2.thread.semaphore.demo07;

/**
 * @author smxknife
 * 2018-12-22
 */
public class ThreadC extends AbsThread {

	public ThreadC(RepastService service) {
		super(service);
	}

	@Override
	public void exe() {
		service.get();
	}
}
