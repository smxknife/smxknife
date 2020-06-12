package com.smxknife.java2.string;

import java.util.StringTokenizer;

/**
 * @author smxknife
 * 2020/4/4
 */
public class StringSplitDemo {
	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			builder.append(i).append(";");
		}
		String string = builder.toString();
		System.out.println(string);

		int splitNum = 10000;
		System.out.println("------ String.split --------");
		long start = System.currentTimeMillis();
		for (int i = 0; i < splitNum; i++) {
			string.split(";");
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		System.out.println("------ StringTokenizer --------");
		start = System.currentTimeMillis();
		StringTokenizer stringTokenizer = new StringTokenizer(string, ";");
		for (int i = 0; i < splitNum; i++) {
			while (stringTokenizer.hasMoreTokens()) {
				stringTokenizer.nextToken();
			}
			stringTokenizer = new StringTokenizer(string, ";");
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

		System.out.println("------ indexOf和subString --------");
		String temp = string;
		start = System.currentTimeMillis();
		for (int i = 0; i < splitNum; i++) {
			while (true) {
				String splitStr = null;
				int j = temp.indexOf(';');
				if (j < 0) break;
				splitStr = temp.substring(0, j);
				temp = temp.substring(j + 1);
			}
			temp = string;
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
