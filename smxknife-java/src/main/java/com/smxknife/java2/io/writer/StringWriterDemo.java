package com.smxknife.java2.io.writer;

import java.io.StringWriter;

/**
 * @author smxknife
 * 2018/11/20
 */
public class StringWriterDemo {
	public static void main(String[] args) {
		StringWriter stringWriter = new StringWriter();
		stringWriter.write("aaaa-0000");
		System.out.println(stringWriter.toString());
	}
}
