package com.smxknife.java2.generics.demo;

import java.time.LocalDate;

/**
 * @author smxknife
 * 2019-04-17
 */
public class Demo {
	public static void main(String[] args) {
		DataInterval interval = new DataInterval();
		Pair<LocalDate> pair = interval;
		pair.setSecond(LocalDate.now());

		Pair<String> stringPair = new Pair<>();
		Pair<Integer> integerPair = new Pair<>();

		System.out.println(stringPair.getClass());
		System.out.println(integerPair.getClass());
		System.out.println(stringPair.getClass().equals(integerPair.getClass()));

		Pair<String>[] table = new Pair[10];
		Object[] arrays = table;
		arrays[0] = "hello";
		arrays[1] = new Pair<Integer>();
	}
}
