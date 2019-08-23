package com.smxknife.java.ex29;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-08-19
 */
public class LambdaException {
	public static void main(String[] args) {
		Stream.of(1, 2, 3, 4).forEach(val -> {
			if (val == 3) {
				throw new RuntimeException("");
			}
			System.out.println(" ----- " + val);
		});
	}
}
