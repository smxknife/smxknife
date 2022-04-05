package com.smxknife.java2.io.writer;

import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/20
 */
public class CharArrayWriterDemo {
	public static void main(String[] args) throws IOException {
		CharArrayWriter charArrayWriter = new CharArrayWriter();
		charArrayWriter.write("你好".toCharArray());
		System.out.println(charArrayWriter.toString());
	}
}
