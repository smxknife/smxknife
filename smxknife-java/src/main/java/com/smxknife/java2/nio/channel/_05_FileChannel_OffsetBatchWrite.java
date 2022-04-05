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
public class _05_FileChannel_OffsetBatchWrite {
	public static void main(String[] args) {

		// 验证FileChannel的write是从当前位置开始写入，并且write之后返回的是写入的长度，不是position
		// writeTest1();

		// 验证write(ByteBuffer)是把ByteBuffer的remaning部分写入通道当前位置
		// writeTest2();

		// 验证write(ByteBuffer)具有同步特性
		writeTest3();


	}

	private static void writeTest3() {
		CountDownLatch latch = new CountDownLatch(5);
		try(FileOutputStream fos = new FileOutputStream(new File(_05_FileChannel_OffsetBatchWrite.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			Stream.iterate(0, idx -> idx + 1).limit(5).forEach(idx -> new Thread(() -> {
				ByteBuffer byteBuffer1 = ByteBuffer.wrap(("abcde " + idx + " ").getBytes());
				ByteBuffer byteBuffer2 = ByteBuffer.wrap((Thread.currentThread().getName() + "\r\n").getBytes());
				try {
					channel.write(new ByteBuffer[] {byteBuffer1, byteBuffer2}, 0, 2);
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
		try(FileOutputStream fos = new FileOutputStream(new File(_05_FileChannel_OffsetBatchWrite.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde".getBytes());
			byteBuffer1.position(2);
			byteBuffer1.limit(3);

			ByteBuffer byteBuffer2 = ByteBuffer.wrap("12345".getBytes());
			byteBuffer2.position(1);
			byteBuffer2.limit(3);

			System.out.println("A fileChannel position = " + channel.position());
			System.out.println("Write 1 返回值 ： " + channel.write(new ByteBuffer[] {byteBuffer1, byteBuffer2}, 1, 1));
			System.out.println("B fileChannel position = " + channel.position());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest1() {
		try(FileOutputStream fos = new FileOutputStream(new File(_05_FileChannel_OffsetBatchWrite.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("************************".getBytes());
			System.out.println("A fileChannel position = " + channel.position());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer));
			System.out.println("B fileChannel position = " + channel.position());
			channel.position(2);

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("123456".getBytes());
			ByteBuffer byteBuffer2 = ByteBuffer.wrap("abcdef".getBytes());
			System.out.println("Write 2 返回值 ： " + channel.write(new ByteBuffer[] {byteBuffer1, byteBuffer2}, 0, 1));
			System.out.println("C fileChannel position = " + channel.position());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
