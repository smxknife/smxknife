package com.smxknife.java2.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author smxknife
 * 2020/7/27
 */
public class LongAdderDemo {
	public static void main(String[] args) {
		LongAdder longAdder = new LongAdder();
		System.out.println(longAdder.longValue());

		longAdder.add(1);

	}
}
