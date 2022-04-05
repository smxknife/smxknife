package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _08_Flip {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 10);
		buffer.put((byte) 11);
		buffer.put((byte) 12);
		buffer.put((byte) 13);

		System.out.println("position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());

		buffer.flip();
		System.out.println("position = " + buffer.position() + " | limit = " + buffer.limit() + " | capacity = " + buffer.capacity());

		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}

	}
}
