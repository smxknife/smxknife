package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author smxknife
 * 2019-01-31
 */
public class FilterInputStreamDemo {
	public static void main(String[] args) {
		String content = "hello_world";
		ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
		FilterInputStream fis = new MyFilterInputStream(bis);
		System.out.println(((MyFilterInputStream) fis).readText());
	}
}

class MyFilterInputStream extends FilterInputStream {

	/**
	 * Creates a <code>FilterInputStream</code>
	 * by assigning the  argument <code>in</code>
	 * to the field <code>this.in</code> so as
	 * to remember it for later use.
	 *
	 * @param in the underlying input stream, or <code>null</code> if
	 *           this instance is to be created without an underlying stream.
	 */
	public MyFilterInputStream(InputStream in) {
		super(in);
	}

	public String readText() {
		String txt = null;
		try {
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			txt = new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return txt;
	}


}
