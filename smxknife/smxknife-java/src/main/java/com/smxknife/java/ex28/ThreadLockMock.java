package com.smxknife.java.ex28;

/**
 * @author smxknife
 * 2019-06-24
 */
public class ThreadLockMock {

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			lock(1, 2, i).start();
			lock(2, 1, i).start();
		}
	}

	public static Thread lock(int a, int b, int idx) {
		return new Thread(() -> {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.printf("a = %s, b = %s\r\n", a, b);
				}
			}

		}, "mockLockThread_" + idx + ":" + a+ "-" + b);
	}
}
