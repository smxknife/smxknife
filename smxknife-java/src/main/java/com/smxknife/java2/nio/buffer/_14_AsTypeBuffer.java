package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _14_AsTypeBuffer {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 0);
		buffer.put((byte) 1);
		buffer.put((byte) 2);
		buffer.put((byte) 3);

		buffer.putInt(4);

		System.out.println("----------------------");

		buffer.flip();

		IntBuffer intBuffer = buffer.asIntBuffer();

		int[] dst = new int[intBuffer.remaining()];

		intBuffer.get(dst);
		for (int i = 0; i < dst.length; i++) {
			System.out.println(dst[i]);
		}



	}
}
