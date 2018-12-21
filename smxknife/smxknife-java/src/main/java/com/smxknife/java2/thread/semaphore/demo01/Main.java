package com.smxknife.java2.thread.semaphore.demo01;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Main {
	public static void main(String[] args) {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		ThreadB b = new ThreadB(service);
		b.setName("B");
		ThreadC c = new ThreadC(service);
		c.setName("C");
		a.start();
		b.start();
		c.start();
	}
}
