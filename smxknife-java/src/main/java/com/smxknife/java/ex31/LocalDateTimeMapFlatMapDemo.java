package com.smxknife.java.ex31;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/8/24
 */
public class LocalDateTimeMapFlatMapDemo {
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		System.out.println(now);
		System.out.println("=============");
		List<LocalTime> collect = Arrays.asList(now, now.plusMinutes(1), now.plusMinutes(2), now.plusMinutes(3), now.plusMinutes(3))
				.stream()
				.distinct()
//				.peek(System.out::println)
				.flatMap(dt -> Stream.of(dt, dt.minusHours(1), dt.plusHours(1)))
				.distinct()
				.peek(System.out::println)
				.collect(Collectors.toList());
		System.out.println("==============");
		collect.stream().forEach(System.out::println);
	}
}
