package com.smxknife.java.ex20;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2018-12-04
 */
public class DurationDemo {
	public static void main(String[] args) {
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusSeconds(10);
		System.out.println(Duration.between(start, end).getSeconds());
	}
}
