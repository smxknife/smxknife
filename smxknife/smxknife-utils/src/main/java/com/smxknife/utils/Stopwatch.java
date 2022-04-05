package com.smxknife.utils;

/**
 * @author smxknife
 * 2019-05-10
 */
public class Stopwatch {

	private final long start;

	public Stopwatch() {
		this.start = System.currentTimeMillis();
	}

	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
}
