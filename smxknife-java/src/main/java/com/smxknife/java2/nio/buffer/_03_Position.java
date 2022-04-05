package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _03_Position {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};

		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		System.out.println("position = " + buffer.position());

		System.out.println("get = " + buffer.get());
		System.out.println("after get position = " + buffer.position());

		buffer.position(3);
		System.out.println("after position(3) get = " + buffer.get());
		System.out.println("position = " + buffer.position());

		buffer.limit(5);
		System.out.println("position = " + buffer.position() + " | limit = " + buffer.limit()) ;

		buffer.position(6);
		System.out.println("position = " + buffer.position() + " | limit = " + buffer.limit()) ;

		System.out.println("get = " + buffer.get());
		System.out.println("get = " + buffer.get());
		System.out.println("get = " + buffer.get());
		System.out.println("get = " + buffer.get());

	}
}
