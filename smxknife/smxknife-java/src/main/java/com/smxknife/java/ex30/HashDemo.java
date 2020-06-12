package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2019/11/15
 */
public class HashDemo {
	public static void main(String[] args) {
		String i = "Aa";
		String j = "BB";
		System.out.println(Integer.toBinaryString(i.hashCode()));
		System.out.println(Integer.toBinaryString(j.hashCode()));
	}
}
