package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-31
 */
public class DataInputStreamDemo2 {
	public static void main(String[] args) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream("hello_world".getBytes()));
		try {
			System.out.println(dis.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
