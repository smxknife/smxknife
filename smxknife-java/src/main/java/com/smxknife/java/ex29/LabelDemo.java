package com.smxknife.java.ex29;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/24
 */
public class LabelDemo {
	public static void main(String[] args) throws InterruptedException {

		int n = 0;
		retry:
		for (;;) {
			System.out.println("outer for: " + n);
			for (;;) {
				System.out.println("inner for: " + n);
				n++;
				if (n == 10) continue retry;
				TimeUnit.SECONDS.sleep(1);
			}
		}
	}
}
