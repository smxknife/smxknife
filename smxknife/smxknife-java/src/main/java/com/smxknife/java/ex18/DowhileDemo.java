package com.smxknife.java.ex18;

/**
 * @author smxknife
 * 2018/11/15
 */
public class DowhileDemo {
	public static void main(String[] args) {
		int i = 0;
		do {
			System.out.println(i);
			if (i == 3) return;
			i++;
		} while (i < 5);
	}
}
