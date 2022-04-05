package com.smxknife.java2._volatile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/6
 */
public class _01_NoVolatile {

	private boolean enable = true;

	public static void main(String[] args) throws IOException {
		final _01_NoVolatile noVolatile = new _01_NoVolatile();
		noVolatile.test();
		System.in.read();
	}

	private void test() {
		new Thread(() -> {
			while (this.enable) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("test2 ...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "test2").start();

		new Thread(() -> {
			while (this.enable) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("test1 ...");
					this.enable = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "test1").start();


	}
}
