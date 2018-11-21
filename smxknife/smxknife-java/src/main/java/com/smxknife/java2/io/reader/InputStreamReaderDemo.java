package com.smxknife.java2.io.reader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author smxknife
 * 2018/11/19
 */
public class InputStreamReaderDemo {
	public static void main(String[] args) throws IOException {

		InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream("你好,hello".getBytes()));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		System.out.println(bufferedReader.readLine());
	}
}
