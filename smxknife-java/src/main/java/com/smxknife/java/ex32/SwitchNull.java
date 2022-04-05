package com.smxknife.java.ex32;

/**
 * @author smxknife
 * 2020/11/10
 */
public class SwitchNull {
	public static void main(String[] args) {
		Integer integer = null;
		switch (integer) {
			case 1:
			default:
				System.out.println("xxx");
		}
	}
}
