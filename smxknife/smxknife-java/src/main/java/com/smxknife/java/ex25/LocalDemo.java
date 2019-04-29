package com.smxknife.java.ex25;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author smxknife
 * 2019-04-22
 */
public class LocalDemo {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 05:45:00")));
	}
}
