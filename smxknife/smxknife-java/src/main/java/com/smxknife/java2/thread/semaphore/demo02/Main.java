package com.smxknife.java2.thread.semaphore.demo02;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Main {
	public static void main(String[] args) {
		Service service = new Service();
		for (int i = 0; i < 10; i++) {
			ThreadA a = new ThreadA(service);
			a.setName("A" + i);
			a.start();
		}
	}
}
