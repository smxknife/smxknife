package com.smxknife.java.ex31;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author smxknife
 * 2020/3/12
 */
public class LongCompare {
	public static void main(String[] args) {
		Integer[] array = new Integer[] {3, 5, 1, 6};
		Integer integer = Arrays.asList(array).stream().sorted(Comparator.comparingInt(Integer::intValue)).findFirst().get();
		System.out.println(integer);
	}
}
