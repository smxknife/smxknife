package com.smxknife.jdk.java.io.closeable;

import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-28
 */
public class CloseableDemo {
	public static void main(String[] args) {
		System.out.println("main start");
		try(ResManager res = new ResManager()) {
			res.test();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main end");
	}
}
