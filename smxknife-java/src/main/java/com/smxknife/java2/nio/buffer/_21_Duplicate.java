package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/29
 */
public class _21_Duplicate {
	public static void main(String[] args) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(16);
		byteBuffer.put((byte)0);
		byteBuffer.put((byte)1);
		byteBuffer.put((byte)2);
		byteBuffer.put((byte)3);
		byteBuffer.put((byte)4);
		byteBuffer.put((byte)5);
		byteBuffer.put((byte)6);
		byteBuffer.put((byte)7);
		byteBuffer.put((byte)8);
		byteBuffer.put((byte)9);
		byteBuffer.put((byte)10);
		byteBuffer.put((byte)11);
		byteBuffer.put((byte)12);
		byteBuffer.put((byte)13);
		byteBuffer.put((byte)14);
		byteBuffer.put((byte)15);

		byteBuffer.position(5);
		byteBuffer.limit(10);

		ByteBuffer duplicate = byteBuffer.duplicate();
		System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
		System.out.printf("duplicate position = %s, limit = %s, capacity = %s\r\n", duplicate.position(), duplicate.limit(), duplicate.capacity());

		duplicate.rewind();
		System.out.printf("duplicate position = %s, limit = %s, capacity = %s\r\n", duplicate.position(), duplicate.limit(), duplicate.capacity());
		while (duplicate.hasRemaining()) {
			System.out.println(duplicate.get());
		}

	}
}
