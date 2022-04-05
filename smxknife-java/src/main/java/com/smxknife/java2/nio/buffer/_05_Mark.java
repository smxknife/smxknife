package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _05_Mark {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};

		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		System.out.println("position = " + buffer.position() + " | limit = " + buffer.limit());

		buffer.get();
		System.out.println("after get position = " + buffer.position() + " | limit = " + buffer.limit());

		buffer.mark();
		System.out.println("after mark position = " + buffer.position() + " | limit = " + buffer.limit());

		buffer.get();
		buffer.get();
		System.out.println("after 2 get position = " + buffer.position() + " | limit = " + buffer.limit());

		buffer.reset();
		System.out.println("after reset | position = " + buffer.position() + " | limit = " + buffer.limit());

	}
}
