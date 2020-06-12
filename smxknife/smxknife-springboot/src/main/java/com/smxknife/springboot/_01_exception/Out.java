package com.smxknife.springboot._01_exception;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2020/6/6
 */
public class Out {

	public static final void out(String... args) {
		String params = Arrays.asList(args).stream().collect(Collectors.joining(", "));
		System.out.println("params = " + params);
	}
}
