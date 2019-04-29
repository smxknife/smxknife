package com.smxknife.jdk.java.io.closeable;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-28
 */
public class ResManager implements Closeable {

	public void test() {
		System.out.println("output");
	}

	@Override
	public void close() throws IOException {
		System.out.println(" close resManager");
	}
}
