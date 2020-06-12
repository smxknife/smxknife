package com.smxknife.java2.string;

/**
 * @author smxknife
 * 2020/4/4
 */
public class StringStartWithDemo {
	public static void main(String[] args) {
		String string = "abcdefg";
		boolean isStartWith = false;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			isStartWith = string.startsWith("abc");
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(isStartWith);

		isStartWith = false;
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			if (string.charAt(0) == 'a' && string.charAt(1) == 'b' && string.charAt(2) == 'c') {
				isStartWith = true;
			}
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(isStartWith);
	}
}
