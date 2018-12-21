package com.smxknife.java2.thread.semaphore.demo06;

/**
 * @author smxknife
 * 2018-12-21
 */
public class MyThread extends Thread {

	private ListPool listPool;

	public MyThread(ListPool listPool) {
		super();
		this.listPool = listPool;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String get = listPool.get();
			System.out.println(Thread.currentThread().getName() + " 取得值 " + get);
			listPool.put(get);
		}
	}
}
