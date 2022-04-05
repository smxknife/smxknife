package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/7/9
 */
public class SwitchStringNull {
	public static void main(String[] args) {
		String msg = null;

		switch (msg) {
			case "1":
				System.out.println("1");
				break;
			case "null":
				System.out.println("null");
				break;
			default:
				System.out.println("def");
				break;
		}
	}
}
