package com.smxknife.java.ex30;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/1/9
 */
public class WithDayOfYear {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now().minusMonths(1);
		System.out.println(now);
		System.out.println(now.withDayOfMonth(1));
		System.out.println(now.withDayOfYear(1));
	}
}
