package com.smxknife.java.ex28;

/**
 * @author smxknife
 * 2019-06-24
 */
public class ThreadWaitMock {
	public static void main(String[] args) {
		ThreadWaitMock mock = new ThreadWaitMock();

		for (int i = 0; i < 5; i++) {
			new Thread(() -> mock.run(), "mockThread_" + i).start();
		}

		try {
			Thread.sleep(100000000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(15000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
