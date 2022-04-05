package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _13_PutType {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 0);
		buffer.put((byte) 1);
		buffer.put((byte) 2);
		buffer.put((byte) 3);

		buffer.putInt(4);

		System.out.println("----------------------");

		buffer.flip();

		byte[] dst = new byte[buffer.remaining()];

		buffer.get(dst);
		for (int i = 0; i < dst.length; i++) {
			System.out.println(dst[i]);
		}



	}
}
