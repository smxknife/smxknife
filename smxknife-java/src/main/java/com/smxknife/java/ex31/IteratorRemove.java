package com.smxknife.java.ex31;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/17
 */
public class IteratorRemove {
	public static void main(String[] args) {
		List<String> string = new ArrayList<>();
		string.add("1");
		string.add("12");
		System.out.println(string);

		Iterator<String> iterator = string.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			if (next.equals("1")) {
				iterator.remove();
			}
		}
		System.out.println(string);
	}
}
