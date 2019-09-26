package com.smxknife.java2.thread.designpattern._03GuardedSuspension.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/24
 */
public class ServerThread extends Thread {
	private final Random random;
	private final RequestQueue requestQueue;

	public ServerThread(RequestQueue requestQueue, String name, long seed) {
		super(name);
		this.requestQueue = requestQueue;
		this.random = new Random(seed);
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Request request = requestQueue.getRequest();
			System.out.println(Thread.currentThread().getName() + " handles " + request);

			try {
				TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
