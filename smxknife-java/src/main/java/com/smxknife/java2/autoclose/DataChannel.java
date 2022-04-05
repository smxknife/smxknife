package com.smxknife.java2.autoclose;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/10/5
 */
public class DataChannel implements Closeable {

	public void handle() {
		System.out.println("DataChannel handle");
	}
	@Override
	public void close() throws IOException {
		System.out.println("DataChannel close");
	}
}
