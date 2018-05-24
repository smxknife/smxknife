package com.smxknife.java.ex7.threadname;

public class TestThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(1000L * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
