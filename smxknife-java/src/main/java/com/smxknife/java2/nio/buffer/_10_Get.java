package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _10_Get {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 10);
		buffer.put((byte) 11);
		buffer.put((byte) 12);
		buffer.put((byte) 13);

		//System.out.println(buffer.get());
		//System.out.println(buffer.get());
		//System.out.println(buffer.get());
		//System.out.println(buffer.get());

		System.out.println("----------------------");

		buffer.flip();
		byte[] dst = new byte[buffer.remaining()];

		buffer.get(dst);
		for (int i = 0; i < dst.length; i++) {
			System.out.println(dst[i]);
		}



	}
}
