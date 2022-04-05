package com.smxknife.java2.excepton;

/**
 * @author smxknife
 * 2018/10/15
 */
public class ExceptionDemo {

	public static void main(String[] args) {
		try {
			System.out.println("demo try");
			return;
		} catch (Exception e) {
			System.out.println("demo catch");
			return;
		} finally {
			System.out.println("demo finally");
			return;
		}

	}
}
