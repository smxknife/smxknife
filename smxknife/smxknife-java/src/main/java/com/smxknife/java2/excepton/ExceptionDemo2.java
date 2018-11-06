package com.smxknife.java2.excepton;

/**
 * @author smxknife
 * 2018/10/15
 */
public class ExceptionDemo2 {

	public static void main(String[] args) {

		int i = testMethod(1);
		System.out.println(i);
	}

	private static int testMethod(int param) {
		try {
			return 5 / param;
		} catch (Exception e) {
			return -1;
		} finally {
			return 10;
		}
	}


}
