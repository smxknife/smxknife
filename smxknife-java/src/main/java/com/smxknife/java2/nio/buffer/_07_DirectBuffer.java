package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _07_DirectBuffer {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};

		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		System.out.println(buffer.isDirect());

		ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(100);
		System.out.println(directByteBuffer.isDirect());
		directByteBuffer.clear();

	}
}
