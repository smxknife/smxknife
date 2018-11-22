package com.smxknife.java2.lambda;

import java.util.Arrays;

/**
 * @author smxknife
 * 2018/11/22
 */
public class ParallelDemo {
	public static void main(String[] args) {
		Arrays.asList(1, 2, 3, 4, 5, 6).stream().parallel()
				.peek(i -> System.out.println(Thread.currentThread().getName() + " peek1 i = " + i))
				.peek(i -> System.out.println(Thread.currentThread().getName() + " peek2 i = " + i))
				.forEach(System.out::println);

	}
}
