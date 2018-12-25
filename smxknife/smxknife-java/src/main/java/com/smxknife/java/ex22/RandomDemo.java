package com.smxknife.java.ex22;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2018-12-25
 */
public class RandomDemo {
	public static void main(String[] args) {
		Random random = new Random();
		IntStream ints = random.ints(10);
		System.out.println(ints.boxed().collect(Collectors.toList()));
		IntStream ints2 = random.ints(0, 10).limit(10);
		System.out.println(Arrays.asList(ints2.boxed().toArray()));
	}
}
