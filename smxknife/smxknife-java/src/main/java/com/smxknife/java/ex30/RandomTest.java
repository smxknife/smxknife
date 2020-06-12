package com.smxknife.java.ex30;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * @author smxknife
 * 2019/11/27
 */
public class RandomTest {
	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		while (true) {
			DoubleStream doubleStream = random.doubles();
			System.out.println(doubleStream.findAny().getAsDouble());
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
