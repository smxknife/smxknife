package com.smxknife.java2.io.input;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;

/**
 * @author smxknife
 * 2018/11/15
 */
public class FilterInputStreamDemo {
	public static void main(String[] args) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello".getBytes());
		FilterInputStream filterInputStream = new BufferedInputStream(byteArrayInputStream);
	}
}
