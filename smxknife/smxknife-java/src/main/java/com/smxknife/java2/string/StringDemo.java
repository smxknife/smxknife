package com.smxknife.java2.string;

import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2018/11/1
 */
public class StringDemo {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("A", "b", "c", "d");
		implicit(strings);
		explicit(strings);
	}

	public static String explicit(List<String> strings) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			builder.append(strings.get(i));
		}
		return builder.toString();
	}

	public static String implicit(List<String> strings) {
		String result = "";
		for (int i = 0; i < strings.size(); i++) {
			result += strings.get(i);
		}
		return result;
	}
}
