package com.smxknife.java2.string;

/**
 * @author smxknife
 * 2018/11/12
 */
public class FormatterDemo {
	public static void main(String[] args) {
		System.out.printf("%-15s %5s %10s\n", "Item", "Query", "Price");
		System.out.printf("%-15s %5s %10s\n", "ItemItemItemItemItemItemItemItemItem", "Query", "Price");

		System.out.printf("%5d", 10);
		System.out.printf("%5b", true);
		System.out.printf("%5b", false);
		System.out.printf("%5b", null);
		System.out.printf("%5b", 0);
		System.out.printf("%5b", 1);
	}
}
