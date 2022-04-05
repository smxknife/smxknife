package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author smxknife
 * 2020/9/29
 */
public class _17_LuanMa {
	public static void main(String[] args) {
		System.out.println("乱码 ---------------");
		byte[] utf8Bytes = "你好，世界".getBytes();
		String name = Charset.defaultCharset().name();
		System.out.println("charset = " + name);
		ByteBuffer utf8ByteBuffer = ByteBuffer.wrap(utf8Bytes);
		CharBuffer utf8CharBuffer = utf8ByteBuffer.asCharBuffer();
		System.out.println(utf8CharBuffer.getClass().getName());
		while (utf8CharBuffer.hasRemaining()) {
			System.out.println(utf8CharBuffer.get());
		}

		System.out.println("方案一 ---------------");

		byte[] utf16BEBytes = "你好，世界".getBytes(StandardCharsets.UTF_16BE);
		ByteBuffer utf16BEByteBuffer = ByteBuffer.wrap(utf16BEBytes);
		CharBuffer utf16BECharBuffer = utf16BEByteBuffer.asCharBuffer();
		System.out.println(utf16BECharBuffer.getClass().getName());
		while (utf16BECharBuffer.hasRemaining()) {
			System.out.println(utf16BECharBuffer.get());
		}

		System.out.println("方案二 ---------------");
		//utf8ByteBuffer.rewind();
		System.out.println("utf8ByteBuffer position = " + utf8ByteBuffer.position() + ", limit = " + utf8ByteBuffer.limit() + ", capacity = " + utf8ByteBuffer.capacity());
		CharBuffer charBuffer = StandardCharsets.UTF_8.decode(utf8ByteBuffer);
		System.out.println("charBuffer position = " + charBuffer.position() + ", limit = " + charBuffer.limit() + ", capacity = " + charBuffer.capacity() + ", offset = " + charBuffer.arrayOffset());
		System.out.println(charBuffer.getClass().getName());
		while (charBuffer.hasRemaining()) {
			System.out.println(charBuffer.get());
		}


	}
}
