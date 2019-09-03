package com.smxknife.java2.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2019/9/1
 */
public class ArrayList_RetainAll {
	public static void main(String[] args) {
		List<String> c1 = Arrays.asList("a", "b", "c");
		List<String> c2 = new ArrayList<>();
		c2.add("b");
		c2.add("c");
		c2.add("d");
		System.out.println(c1);
		System.out.println(c2);

		c2.retainAll(c1);
		System.out.println(c2);
	}
}
