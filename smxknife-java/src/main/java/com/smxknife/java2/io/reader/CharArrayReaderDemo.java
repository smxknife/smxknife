package com.smxknife.java2.io.reader;

import java.io.CharArrayReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 * @author smxknife
 * 2018/11/19
 */
public class CharArrayReaderDemo {
	public static void main(String[] args) throws IOException {
		String content = "helLo-HELLO";
		CharArrayReader charArrayReader = new CharArrayReader(content.toCharArray());
		System.out.println((char) charArrayReader.read());
		System.out.println(charArrayReader.ready());
		System.out.println(charArrayReader.skip(2));
//		System.out.println((char) charArrayReader.read());
		CharBuffer charBuffer = CharBuffer.allocate(3);
		System.out.println(charArrayReader.read(charBuffer));
		charBuffer.flip();
		System.out.println(charBuffer.toString());

		System.out.println("====================");

		charArrayReader = new CharArrayReader("world".toCharArray());
		char[] chars = new char[5];
		charArrayReader.read(chars);
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}

	}
}
