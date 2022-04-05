package com.smxknife.java2.thread.designpattern._03GuardedSuspension.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 发送请求线程
 * @author smxknife
 * 2019/9/24
 */
public class ClientThread extends Thread {
	private final Random random;
	private final RequestQueue requestQueue;

	public ClientThread(RequestQueue requestQueue, String name, long seed) {
		super(name);
		this.requestQueue = requestQueue;
		this.random = new Random(seed);
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Request request = new Request("No." + i);
			System.out.println(Thread.currentThread().getName() + " requests " + request);
			requestQueue.putRequest(request);

			try {
				TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
