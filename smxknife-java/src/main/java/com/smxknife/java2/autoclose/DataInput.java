package com.smxknife.java2.autoclose;

import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/10/5
 */
public class DataInput implements Closeable {

	@Getter
	private DataChannel channel;

	public DataInput() {
		channel = new DataChannel();
	}

	public void handle() {
		System.out.println("DataInput handle");
	}

	@Override
	public void close() throws IOException {
		System.out.println("DataInput close");
	}
}
