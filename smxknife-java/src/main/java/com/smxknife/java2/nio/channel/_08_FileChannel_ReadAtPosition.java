package com.smxknife.java2.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _08_FileChannel_ReadAtPosition {
	public static void main(String[] args) {
		// 验证int read(ByteBuffer)返回值的意义
		/**
		 * > 0: 代表当前位置向ByteBuffer读取的字节数
		 * = 0: 代表通道中没有读取到任何数据，也就是0字节，有可能是ByteBuffer中没有剩余空间了
		 * < 0: 代表到达流的末尾
		 */
		// readTest1();

		// 验证int read(ByteBuffer)将字节放入ByteBuffer当前位置
		// readTest3();

		// 验证int read(ByteBuffer)具有同步性
		// readTest4();

		// 验证int read(ByteBuffer)从通道读取的数据大于缓冲区的容量，只能读取remaining的量
		// readTest5();

		// 验证int read(ByteBuffer)从通道读取的数据只能放入缓冲区remaining中
		// readTest6();

	}

	private static void readTest6() {
		try(FileInputStream fis = new FileInputStream(new File(_08_FileChannel_ReadAtPosition.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
			byteBuffer.position(50);
			byteBuffer.limit(60);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			int readLength4 = channel.read(byteBuffer, 6);
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			System.out.println(new String(byteBuffer.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest5() {
		try(FileInputStream fis = new FileInputStream(new File(_08_FileChannel_ReadAtPosition.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.allocate(12);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			int readLength4 = channel.read(byteBuffer, 6);
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			System.out.println(new String(byteBuffer.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest4() {
		CountDownLatch latch = new CountDownLatch(12);
		try(FileInputStream fis = new FileInputStream(new File(_08_FileChannel_ReadAtPosition.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			Stream.iterate(0, idx -> idx + 1).limit(12).forEach(idx -> new Thread(() -> {
				ByteBuffer byteBuffer = ByteBuffer.allocate(6);
				try {
					int j = 0;
					while ((j = channel.read(byteBuffer, idx * byteBuffer.remaining() + 6)) != -1) {
						byte[] array = byteBuffer.array();
						System.out.println(new String(array) + " | " + Thread.currentThread().getName());
						byteBuffer.clear();

					}
					latch.countDown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}, "th_" + idx).start());

			latch.await();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void readTest3() {
		try(FileInputStream fis = new FileInputStream(new File(_08_FileChannel_ReadAtPosition.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("********".getBytes());
			byteBuffer.position(2);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			int readLength4 = channel.read(byteBuffer, 6);
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			System.out.println(new String(byteBuffer.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest1() {
		// CountDownLatch latch = new CountDownLatch(5);
		try(FileInputStream fis = new FileInputStream(new File(_08_FileChannel_ReadAtPosition.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.allocate(6);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
			int readLength = channel.read(byteBuffer, 7);
			System.out.println("readLength = " + readLength);
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());
			System.out.printf("channel position = %s\r\n", channel.position());

			// 再次read之后返回值是0，因为ByteBuffer没有remaining剩余空间
			int readLength2 = channel.read(byteBuffer, 7);
			System.out.printf("readLength2 = %s, 返回值是0，因为ByteBuffer没有remaining剩余空间\r\n", readLength2);
			byteBuffer.clear();
			System.out.printf("after clear byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());

			int readLength3 = channel.read(byteBuffer, 73);
			System.out.printf("readLength3 = %s, 返回值是-1，因为到达流的末尾\r\n", readLength3);
			System.out.printf("byteBuffer position = %s, limit = %s, capacity = %s\r\n", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity());


			// latch.await();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
