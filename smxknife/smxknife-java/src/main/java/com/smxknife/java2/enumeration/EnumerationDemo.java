package com.smxknife.java2.enumeration;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author smxknife
 * 2018/11/15
 */
public class EnumerationDemo {
	public static void main(String[] args) {
		Vector<String> vector = new Vector<>();
		vector.add("hello");
		vector.add("world");
		Enumeration<String> elements = vector.elements();
		while (elements.hasMoreElements()) {
			System.out.println(elements.nextElement());
		}
	}
}
