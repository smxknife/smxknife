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
public class _04_FileChannel_BatchRead {
	public static void main(String[] args) {
		// 验证int read(ByteBuffer)返回值的意义
		/**
		 * > 0: 代表当前位置向ByteBuffer读取的字节数
		 * = 0: 代表通道中没有读取到任何数据，也就是0字节，有可能是ByteBuffer中没有剩余空间了
		 * < 0: 代表到达流的末尾
		 */
		// readTest1();

		// 验证int read(ByteBuffer)从通道当前位置开始读取
		// readTest2();

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
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
			byteBuffer1.position(50);
			byteBuffer1.limit(60);

			ByteBuffer byteBuffer2 = ByteBuffer.allocate(100);
			byteBuffer2.position(10);
			byteBuffer2.limit(15);
			System.out.printf("channel position = %s\r\n", channel.position());
			long readLength4 = channel.read(new ByteBuffer[] {byteBuffer1, byteBuffer2});
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.println(new String(byteBuffer1.array()));
			System.out.println(new String(byteBuffer2.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest5() {
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.allocate(3);
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(2);
			System.out.printf("channel position = %s\r\n", channel.position());

			long readLength4 = channel.read(new ByteBuffer[]{byteBuffer1, byteBuffer2});
			System.out.printf("readLength4 = %s\r\n", readLength4);

			System.out.println(new String(byteBuffer1.array()));
			System.out.println(new String(byteBuffer2.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest4() {
		CountDownLatch latch = new CountDownLatch(12);
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			Stream.iterate(0, idx -> idx + 1).limit(12).forEach(idx -> new Thread(() -> {
				ByteBuffer byteBuffer1 = ByteBuffer.allocate(6);
				ByteBuffer byteBuffer2 = ByteBuffer.allocate(6);
				ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
				try {
					long j = 0;
					while ((j = channel.read(byteBuffers)) != -1) {
						System.out.println(new String(byteBuffer1.array()) + new String(byteBuffer2.array()) + " | " + Thread.currentThread().getName());
						byteBuffer1.clear();
						byteBuffer2.clear();

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
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("********".getBytes());
			ByteBuffer byteBuffer2 = ByteBuffer.wrap("########".getBytes());
			byteBuffer1.position(2);
			byteBuffer2.position(3);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());

			long readLength4 = channel.read(new ByteBuffer[] {byteBuffer1, byteBuffer2});
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());

			System.out.println("byteByffer1 = " + new String(byteBuffer1.array()));
			System.out.println("byteByffer2 = " + new String(byteBuffer2.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest2() {
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.allocate(6);
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(6);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());

			channel.position(4);
			System.out.printf("channel position = %s\r\n", channel.position());
			long readLength4 = channel.read(new ByteBuffer[] {byteBuffer1, byteBuffer2});
			System.out.printf("readLength4 = %s\r\n", readLength4);
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());

			System.out.println(new String(byteBuffer1.array()));
			System.out.println(new String(byteBuffer2.array()));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readTest1() {
		// CountDownLatch latch = new CountDownLatch(5);
		try(FileInputStream fis = new FileInputStream(new File(_04_FileChannel_BatchRead.class.getClassLoader().getResource("channel/_02_FileChannel_read").getPath()));
		    FileChannel channel = fis.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.allocate(6);
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(12);
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());
			long readLength = channel.read(new ByteBuffer[] {byteBuffer1, byteBuffer2});
			System.out.println("readLength = " + readLength);
			System.out.printf("byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());
			System.out.printf("channel position = %s\r\n", channel.position());
			System.out.println("byteBuffer1 content = " + new String(byteBuffer1.array()));
			System.out.println("byteBuffer2 content = " + new String(byteBuffer2.array()));

			// 再次read之后返回值是0，因为ByteBuffer没有remaining剩余空间
			long readLength2 = channel.read(new ByteBuffer[] {byteBuffer1, byteBuffer2});
			System.out.printf("readLength2 = %s, 返回值是0，因为ByteBuffer没有remaining剩余空间\r\n", readLength2);
			byteBuffer1.clear();
			byteBuffer2.clear();
			System.out.printf("after clear byteBuffer1 position = %s, limit = %s, capacity = %s\r\n", byteBuffer1.position(), byteBuffer1.limit(), byteBuffer1.capacity());
			System.out.printf("after clear byteBuffer2 position = %s, limit = %s, capacity = %s\r\n", byteBuffer2.position(), byteBuffer2.limit(), byteBuffer2.capacity());
			channel.read(new ByteBuffer[] {ByteBuffer.allocate(100), ByteBuffer.allocate(100)});
			long readLength3 = channel.read(new ByteBuffer[] {ByteBuffer.allocate(100), ByteBuffer.allocate(100)});
			System.out.printf("readLength3 = %s, 返回值是-1，因为到达流的末尾\r\n", readLength3);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
