package com.smxknife.java2.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _07_FileChannel_WriteAtPosition {
	public static void main(String[] args) {
		// 注：所有指定位置的write，channel的position保持不变

		// 验证FileChannel的write是从当前位置开始写入，并且write之后返回的是写入的长度，不是position
		// 如果指定的位置前面有未指定的内容，则会以NUL的形式存在（其实就是未指定）
		// writeTest1();

		// 验证write(ByteBuffer)是把ByteBuffer的remaning部分写入通道当前位置
		// writeTest2();

		// 验证write(ByteBuffer)具有同步特性
		// writeTest3();


	}

	private static void writeTest3() {
		CountDownLatch latch = new CountDownLatch(5);
		try(FileOutputStream fos = new FileOutputStream(new File(_07_FileChannel_WriteAtPosition.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			Stream.iterate(0, idx -> idx + 1).limit(5).forEach(idx -> new Thread(() -> {
				ByteBuffer byteBuffer = ByteBuffer.wrap(("abcde " + idx + " " + Thread.currentThread().getName() + " ").getBytes());
				try {
					System.out.println(Thread.currentThread().getName() + " write at " + (idx * byteBuffer.remaining() + 5 + idx * 5) + ", length = " + byteBuffer.remaining());
					channel.write(byteBuffer, idx * byteBuffer.remaining() + idx * 5 + 5);
					latch.countDown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}, "th-" + idx).start());

			latch.await();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest2() {
		try(FileOutputStream fos = new FileOutputStream(new File(_07_FileChannel_WriteAtPosition.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("**********\r\n0123456789".getBytes());
			ByteBuffer byteBuffer2 = ByteBuffer.wrap("12345".getBytes());
			byteBuffer2.position(1);
			byteBuffer2.limit(4);

			System.out.println("A fileChannel position = " + channel.position());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer1));
			System.out.println("B fileChannel position = " + channel.position());
			channel.position(0);

			System.out.println("Write 2 返回值 ： " + channel.write(byteBuffer2, 7));
			System.out.println("C fileChannel position = " + channel.position());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest1() {
		try(FileOutputStream fos = new FileOutputStream(new File(_07_FileChannel_WriteAtPosition.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("********\r\n012345678".getBytes());
			channel.write(byteBuffer1);
			channel.position(0);

			ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
			System.out.println("A fileChannel position = " + channel.position());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer, 2));
			System.out.println("B fileChannel position = " + channel.position());
//			channel.position(2);
//			byteBuffer.rewind();
//			System.out.println("Write 2 返回值 ： " + channel.write(byteBuffer));
//			System.out.println("C fileChannel position = " + channel.position());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
