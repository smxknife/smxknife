package com.smxknife.java.ex27;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2019-05-23
 */
public class Demo5 {
	public static void main(String[] args) {
		float data = 0.75f;

		IntStream.of(1, 2, 3).mapToObj(Integer::valueOf).collect(Collectors.toList());

		int sum = IntStream.of(1, 2, 3).reduce((x, y) -> {
			System.out.printf("x = %s, y = %s\r\n", x, y);
			return x + y;
		}).orElse(0);
		System.out.println(sum);
		System.out.println("----------------");

		int sum2 = IntStream.of(1, 2, 3)
				.reduce((x, y) -> {
					System.out.printf("x = %s, y = %s\r\n", x, y);
					return x + y * 2;
				}).orElse(0);
		System.out.println(sum2);
		System.out.println("----------------");

		int sum3 = IntStream.of(1, 2, 3)
				.reduce(0, (x, y) -> {
					System.out.printf("x = %s, y = %s\r\n", x, y);
					return x + y * 2;
				});
		System.out.println(sum3);
	}
}
