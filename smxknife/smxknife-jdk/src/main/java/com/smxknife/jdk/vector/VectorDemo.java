package com.smxknife.jdk.vector;

import java.util.Vector;

/**
 * @author smxknife
 * 2018/9/19
 */
public class VectorDemo {
	public static void main(String[] args) {
		Vector<String> vector = new Vector<>();
		System.out.println(vector.size());

		for (int i = 0; i < 1000; i++) {
			vector.add(String.valueOf(i));
			System.out.println(">> size: " + vector.size());
		}
	}
}
