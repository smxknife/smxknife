package com.smxknife.java2.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/7/27
 */
public class AtomicIntegerDemo {
	public static void main(String[] args) {
		AtomicInteger integer = new AtomicInteger();
		System.out.println(integer.getAndIncrement());
	}
}
