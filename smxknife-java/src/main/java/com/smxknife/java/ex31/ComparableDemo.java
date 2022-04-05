package com.smxknife.java.ex31;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2020/8/22
 */
public class ComparableDemo {
	public static void main(String[] args) {
		List<Integer> collect = Arrays.asList(5, 8, 12, 1, 9, null).stream().sorted((v1, v2) -> {
			System.out.println("v1 = " + v1 + " | v2 = " + v2);
			return v1 > v2 ? 1 : -1;
		}).collect(Collectors.toList());
		System.out.println(collect);
	}
}
