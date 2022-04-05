package com.smxknife.java.ex30;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author smxknife
 * 2019/11/8
 */
public class ThreadLocalRandomDemo {
	public static void main(String[] args) {
		long l = ThreadLocalRandom.current().nextLong(60 * 1000);
		System.out.println(l);

		System.out.println(new Date());
		System.out.println(new Date(System.currentTimeMillis() + l));

		for (int i = 0; i < 10; i++) {
			int i1 = ThreadLocalRandom.current().nextInt(1, 3);
			System.out.println(i1);
		}

	}
}
