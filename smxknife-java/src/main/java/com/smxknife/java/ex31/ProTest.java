package com.smxknife.java.ex31;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author smxknife
 * 2020/3/3
 */
public class ProTest {
	public static void main(String[] args) {
		long i=0xfffL;double k=0.9239d;
		System.out.println(i);
		System.out.println(k);

		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM")));
	}
}
