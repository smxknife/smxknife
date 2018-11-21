package com.smxknife.java2.io.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author smxknife
 * 2018/11/20
 */
public class BufferedWriterDemo {
	public static void main(String[] args) throws IOException {
		StringWriter stringWriter = new StringWriter();
		BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

		bufferedWriter.write("hello");
		bufferedWriter.newLine();
		bufferedWriter.write("world");
		bufferedWriter.flush();

		System.out.println(stringWriter.toString());
		System.out.println("-----------------");
		System.out.println(bufferedWriter.toString());
		stringWriter.close();
		bufferedWriter.close();
	}
}
