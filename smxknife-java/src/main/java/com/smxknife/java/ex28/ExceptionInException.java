package com.smxknife.java.ex28;

/**
 * @author smxknife
 * 2019-08-08
 */
public class ExceptionInException {
	public static void main(String[] args) {
		try {
			try {
				throw new Exception("xxx");
			} catch (Exception e) {
				int i = 0;
				int b = 1;
				b = b / i;
				System.out.println("exception:1");
			}
		} catch (Exception oe) {
			System.out.println("exception:2");
		}

		System.out.println("final");


	}
}
