package com.smxknife.java.ex30;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author smxknife
 * 2019/9/24
 */
public class LocalDateTimeDemo {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		System.out.println();

		long l = now.plusHours(1).withMinute(0).withSecond(0).withNano(0)
				.toInstant(ZoneId.systemDefault().getRules().getOffset(now)).toEpochMilli();

		System.out.println();
		long l1 = now.withMinute(0).withSecond(0).withNano(0)
				.toInstant(ZoneId.systemDefault().getRules().getOffset(now)).toEpochMilli();

		System.out.println((l - l1) / 1000);
	}
}
