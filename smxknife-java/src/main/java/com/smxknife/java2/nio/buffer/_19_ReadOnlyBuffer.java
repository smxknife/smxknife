package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/29
 */
public class _19_ReadOnlyBuffer {
	public static void main(String[] args) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
		System.out.printf("byteBuffer readonly = %s, position = %s, limit = %s, capacity = %s\r\n", byteBuffer.isReadOnly(), byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
		System.out.printf("readOnlyBuffer readonly = %s, position = %s, limit = %s, capacity = %s\r\n", readOnlyBuffer.isReadOnly(), readOnlyBuffer.position(), readOnlyBuffer.limit(), readOnlyBuffer.capacity());

		byteBuffer.putInt(1);
		System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
		System.out.printf("readOnlyBuffer position = %s, limit = %s, capacity = %s\r\n", readOnlyBuffer.position(), readOnlyBuffer.limit(), readOnlyBuffer.capacity());
		byteBuffer.rewind();
		while (byteBuffer.hasRemaining()) {
			System.out.print(byteBuffer.get());
		}
		System.out.println();
		while (readOnlyBuffer.hasRemaining()) {
			System.out.print(readOnlyBuffer.get());
		}

		readOnlyBuffer.putInt(1);

	}
}
