package com.smxknife.java.ex27;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-05-09
 */
public class Demo3 {
	public static void main(String[] args) {
		int[] a = new int[10];
		a[0] = 1;
		a[3] = 4;
		System.out.println(a.length);

		int[] copyOf = Arrays.copyOf(a, 5);

//		System.out.println("_____");
//		System.out.println(a);
//		System.out.println(copyOf);
//		System.out.println("_____");

		System.out.println(copyOf.length);
		Integer[] ss = new Integer[copyOf.length];
		for (int i = 0; i < copyOf.length; i++) {
			ss[i] = copyOf[i];
		}

		Stream.of(Arrays.asList(copyOf)).forEach(System.out::println);
		Stream.of(Arrays.asList(ss)).forEach(System.out::println);
	}
}
