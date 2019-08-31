package com.smxknife.java2.thread.semaphore.demo05;

import java.util.concurrent.Semaphore;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(10);

		MyService myService = new MyService();
		myService.testMethod(semaphore);

	}
}

class MyService {

	public void testMethod(Semaphore semaphore) throws InterruptedException {
		System.out.println(semaphore.availablePermits());
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		System.out.println(semaphore.drainPermits() + " - " + semaphore.availablePermits());
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
	}
}
