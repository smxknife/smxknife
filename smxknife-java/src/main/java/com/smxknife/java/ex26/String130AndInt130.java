package com.smxknife.java.ex26;

import java.util.Arrays;

/**
 * @author smxknife
 * 2019-05-07
 */
public class String130AndInt130 {
	static int[] int1 = new int[Integer.MAX_VALUE / 100];
	static String[] string1 = new String[Integer.MAX_VALUE / 100];

	public static void main(String[] args) {
		System.out.println("begin");

		Arrays.fill(int1, 130);
		Arrays.fill(string1, "130");

//		for (int i = 0; i < int1.length; i++) {
//			int1[i] = 1;
//			System.out.println("current index = " + i);
//		}

		System.out.println("filled");
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished");
	}
}
