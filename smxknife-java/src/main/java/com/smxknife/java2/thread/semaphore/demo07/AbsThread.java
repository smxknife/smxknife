package com.smxknife.java2.thread.semaphore.demo07;

/**
 * @author smxknife
 * 2018-12-22
 */
public abstract class AbsThread extends Thread {

	protected RepastService service;

	public AbsThread(RepastService service) {
		this.service = service;
	}

	@Override
	public void run() {
		exe();
	}

	public abstract void exe();
}
