package com.smxknife.java2.io.reader;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/19
 */
public class BufferedReaderDemo {
	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new CharArrayReader("你好，世界\r\nhello world\r\nNew line".toCharArray()));
		System.out.println(bufferedReader.readLine());
		bufferedReader.lines().forEach(System.out::print);


	}
}
