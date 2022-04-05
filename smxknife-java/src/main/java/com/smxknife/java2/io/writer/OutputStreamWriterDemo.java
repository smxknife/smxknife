package com.smxknife.java2.io.writer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author smxknife
 * 2018/11/19
 */
public class OutputStreamWriterDemo {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
		outputStreamWriter.write("nnnnnnn");
		outputStreamWriter.flush();

		System.out.println(outputStreamWriter.toString());
		System.out.println("-----------");
		System.out.println(byteArrayOutputStream.toString());
		outputStreamWriter.close();
		byteArrayOutputStream.close();
	}
}
