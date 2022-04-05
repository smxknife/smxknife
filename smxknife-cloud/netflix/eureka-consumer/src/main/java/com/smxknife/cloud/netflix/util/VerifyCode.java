package com.smxknife.cloud.netflix.util;

/**
 * @author smxknife
 * 2021/5/25
 */
public class VerifyCode {
	public static void main(String[] args) {
		final int num = 10000000;

		final long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		}
		final long end = System.currentTimeMillis();
		System.out.println(" 1 = " + (end - start));

		final long start1 = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			String code1 = String.valueOf((Math.random()*9+1)*Math.pow(10, 5));

		}
		final long end1 = System.currentTimeMillis();
		System.out.println(" 2 = " + (end1 - start1));
	}
}
