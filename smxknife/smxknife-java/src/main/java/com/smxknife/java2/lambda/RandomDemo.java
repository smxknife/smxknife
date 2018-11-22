package com.smxknife.java2.lambda;

import java.util.Random;

/**
 * @author smxknife
 * 2018/11/22
 */
public class RandomDemo {
	public static void main(String[] args) {
		Random random = new Random();
//		random.ints().forEach(System.out::println);
		random.ints(10).forEach(System.out::print);
		System.out.println();
		random.ints(1, 3).limit(10).forEach(System.out::print);
		System.out.println();
		random.ints(10, 5, 10).forEach(System.out::print);
		System.out.println();
		random.longs(10).forEach(System.out::print);
	}
}
