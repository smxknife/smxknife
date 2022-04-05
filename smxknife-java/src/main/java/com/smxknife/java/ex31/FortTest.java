package com.smxknife.java.ex31;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author smxknife
 * 2020/8/7
 */
public class FortTest {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now().withMinute(28);
		System.out.println(now);
		System.out.println(format(now));
	}

	public static String format(LocalDateTime dateTime) {
		int minute = dateTime.getMinute();
		int mod = minute % 5;
		int diff = 5 - mod;
		minute = (diff > 1) ? (minute - mod) : (minute + diff);
		return dateTime.withMinute(minute).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
	}
}
