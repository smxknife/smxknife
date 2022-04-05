package com.smxknife.java.ex20;

import java.time.LocalDate;

/**
 * @author smxknife
 * 2018-12-07
 */
public class MinusDemo {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		System.out.println(now);
		System.out.println(now.minusDays(7));
	}
}
