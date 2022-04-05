package com.smxknife.java2.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author smxknife
 * 2020/9/29
 */
public class _18_Order {
	public static void main(String[] args) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		System.out.println(byteBuffer.order().toString());
		byteBuffer.putInt(1);
		byteBuffer.rewind();
		while (byteBuffer.hasRemaining()) {
			System.out.print(byteBuffer.get());
		}
		System.out.println();

		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println(byteBuffer.order().toString());
		byteBuffer.clear();
		byteBuffer.putInt(1);
		byteBuffer.rewind();
		while (byteBuffer.hasRemaining()) {
			System.out.print(byteBuffer.get());
		}

	}
}
