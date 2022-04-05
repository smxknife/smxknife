package com.smxknife.java2.lambda;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-24
 */
public class IteratorDemo {
	public static void main(String[] args) {
		Stream.iterate(0, idx -> idx + 1).limit(4).forEach(System.out::println);
	}
}
