package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/9
 */
public class SplitDemo {
	public static void main(String[] args) {
		String demo = "aaa, bbb, ccc";
		String[] split = demo.split(",");
		for (String s : split) {
			System.out.println("|" + s + "| length: " + s.length());
		}
	}
}
