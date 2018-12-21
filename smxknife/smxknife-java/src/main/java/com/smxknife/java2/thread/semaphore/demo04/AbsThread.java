package com.smxknife.java2.thread.semaphore.demo04;

/**
 * @author smxknife
 * 2018-12-21
 */
public class AbsThread extends Thread {

	protected boolean interrupt;

	protected Service service;

	@Override
	public void run() {
		if (interrupt)
			service.testInterruptMethod();
		else
			service.testUnInterruptMethod();
	}

}
