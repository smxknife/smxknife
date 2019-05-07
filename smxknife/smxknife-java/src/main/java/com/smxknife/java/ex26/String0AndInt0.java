package com.smxknife.java.ex26;

/**
 * @author smxknife
 * 2019-05-07
 */
public class String0AndInt0 {
	static int[] int1 = new int[Integer.MAX_VALUE / 100];
	static String[] string1 = new String[Integer.MAX_VALUE / 100];

	public static void main(String[] args) {
		System.out.println("begin");

		System.out.println("filled");
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished");
	}
}
