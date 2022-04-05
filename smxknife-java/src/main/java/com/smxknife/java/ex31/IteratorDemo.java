package com.smxknife.java.ex31;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author smxknife
 * 2020/7/7
 */
public class IteratorDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1", "2", "3");

		String val = "";
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			val = iterator.next();
		}
		System.out.println("-------");
		System.out.println(val);
	}
}
