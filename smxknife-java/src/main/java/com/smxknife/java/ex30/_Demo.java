package com.smxknife.java.ex30;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Demo {
	public static void main(String[] args) {
		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> new Thread(() -> {Bird.INSTANCE.print();}, "th_" + idx).start());

		System.out.println(Bird.INSTANCE == Bird.INSTANCE);
	}
}
