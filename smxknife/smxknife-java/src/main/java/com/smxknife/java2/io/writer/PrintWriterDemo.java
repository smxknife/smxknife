package com.smxknife.java2.io.writer;

import java.io.PrintWriter;

/**
 * @author smxknife
 * 2018/11/20
 */
public class PrintWriterDemo {
	public static void main(String[] args) {
		PrintWriter printWriter = new PrintWriter(System.out, true);
		printWriter.println(1);
	}
}
