package com.smxknife.java.ex32;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2021/1/10
 */
public class SyncTest {
	public static void main(String[] args) {
		SyncTest test = new SyncTest();

		Stream.iterate(0, idx -> idx + 1).limit(5).forEach(idx -> new Thread(() -> {
			statictest();
			test.mtest();
			test.objtest();
		}).start());

	}

	public synchronized void mtest() {
		System.out.println("xxxxx");
	}

	public void objtest() {
		Object obj = new Object();
		synchronized (obj) {
			System.out.println("yyyyy");
		}
	}

	public synchronized static void statictest() {
		System.out.println("zzzzz");
	}
}
