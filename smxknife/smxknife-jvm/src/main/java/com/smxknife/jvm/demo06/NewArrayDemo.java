package com.smxknife.jvm.demo06;

public class NewArrayDemo {
	public static void main(String[] args) {
		int buffer[];
		int size = 100;
		int value = 12;
		buffer = new int[size];
		buffer[10] = value;
		value = buffer[11];
		System.out.println(value);
	}
}
