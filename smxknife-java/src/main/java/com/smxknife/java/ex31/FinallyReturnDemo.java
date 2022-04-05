package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/7/24
 */
public class FinallyReturnDemo {
	public static void main(String[] args) {
		int res = finallyReturn();
		System.out.println(res);
	}

	private static int finallyReturn() {
		try {
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}
	}
}
