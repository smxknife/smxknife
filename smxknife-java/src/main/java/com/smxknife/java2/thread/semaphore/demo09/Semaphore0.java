package com.smxknife.java2.thread.semaphore.demo09;

import java.util.concurrent.Semaphore;

/**
 * @author smxknife
 * 2021/6/19
 */
public class Semaphore0 {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(0);

		try {
			semaphore.release();
			System.out.println(semaphore.availablePermits());
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
