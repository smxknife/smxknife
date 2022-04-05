package com.smxknife.java2.thread.semaphore.demo03;

import java.util.concurrent.Semaphore;

/**
 * availablePermits获取许可数量，但并不是固定的
 * @author smxknife
 * 2018-12-21
 */
public class Main {

	static Semaphore semaphore = new Semaphore(5);
	public static void main(String[] args) throws InterruptedException {
		System.out.println(semaphore.availablePermits());
		semaphore.acquire();
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.acquire();
		semaphore.acquire();
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		semaphore.release();
		semaphore.release();
		semaphore.release();
		System.out.println(semaphore.availablePermits());
		semaphore.release();
		semaphore.release();
		System.out.println(semaphore.availablePermits());

	}
}
