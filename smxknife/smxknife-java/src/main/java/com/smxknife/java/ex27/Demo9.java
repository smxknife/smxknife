package com.smxknife.java.ex27;

/**
 * @author smxknife
 * 2019-06-04
 */
public class Demo9 {
	public static void main(String[] args) {
		Thread[] threads = new Thread[3];

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> UtilTest.test2(), "test_" + i);
			threads[i] = thread;
			thread.start();
		}


	}
}
