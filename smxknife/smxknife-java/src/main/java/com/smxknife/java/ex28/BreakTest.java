package com.smxknife.java.ex28;

/**
 * @author smxknife
 * 2019-08-06
 */
public class BreakTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println("i == " + i);
			for (int j = 0; j < 3; j++) {
				System.out.println("j == " + j);
				break;
			}
		}
	}
}
