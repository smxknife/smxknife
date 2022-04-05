package com.smxknife.java2.thread.semaphore.demo06;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Main {
	public static void main(String[] args) {
		ListPool listPool = new ListPool();
		MyThread[] threads = new MyThread[12];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThread(listPool);
		}
		Stream.of(threads).forEach(MyThread::start);
	}
}
