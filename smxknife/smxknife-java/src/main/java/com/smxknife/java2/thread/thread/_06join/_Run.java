package com.smxknife.java2.thread.thread._06join;

/**
 * @author smxknife
 * 2019/10/14
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread();
		thread.join();
		thread.join(1000);
	}
}
