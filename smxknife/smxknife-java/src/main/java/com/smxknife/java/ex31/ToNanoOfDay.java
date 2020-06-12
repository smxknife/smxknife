package com.smxknife.java.ex31;

import java.time.LocalTime;

/**
 * @author smxknife
 * 2020/5/7
 */
public class ToNanoOfDay {
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		System.out.println(now);

		long l = now.toNanoOfDay();
		System.out.println(l);
		LocalTime time = now.plusSeconds(5);
		System.out.println(time);
		long l1 = time.toNanoOfDay();
		System.out.println(l1);
		System.out.println(l1 - l);


	}
}
