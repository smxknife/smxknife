package com.smxknife.java2.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _13_FileChannel_FileLock_2 {
	public static void main(String[] args) {

		// 获取此通道的文件给定区域上的锁定

		// 在可以锁定该区域之前、已关闭此通道之前、或已中断调用线程之前，将阻塞此方法调用
		// 在此方法调用期间，如果另一个线程关闭了此通道，则抛出AsynchronousCloseException
		// 如果在等待获取锁定的同时中断了调用线程，则将状态设置为中断并抛出FileLockInterruptionException异常
		// 如果调用此方法是已设置调用方的中断状态，则立即抛出该异常，不更改线程的调用状态

		// 由position和size参数所指定的区域无须包含在实际的底层文件中，设置无须与文件重叠。
		// 锁定区域的大小是固定的；如果某个已锁定区域最初包含整个文件，并且文件因扩大而超出了该区域，则该锁定不覆盖此文件的新部分。如果期望文件大小扩容并且要求锁定整个文件，则应锁定position从零开始，size传入大于或等于预计文件的最大值
		// 零参数的lock方法只是锁定大小为Long.MAX_VALUE的区域

		// 文件锁要么是独占的，要么是共享的
		// 共享锁定可以阻止其他并发运行的程序获取重叠的独占锁定，但允许该程序偶去重叠的共享锁定
		// 独占锁定则阻止其他程序获取共享锁定或者独占锁定的重叠部分
		// 某些操作系统不支持共享锁定，自动对共享锁定转换为独占锁定的请求，可以通过调用所得的锁定对象的isShared()方法来测试新获取的锁定是共享的还是独占的
		// 文件锁定是以整个Java虚拟机来保持的。但是不适用于控制同一虚拟机内多个线程对文件的访问

		// 验证FileLock lock(long position, long size, boolean shared)是同步的
		// 需要FileLock_1先运行，然后再启动FileLock_2，观察FileLock_2的输出，只有在FileLock_1执行完（释放了锁），才能获取锁继续执行，之前一直处于阻塞状态
		// lockTest1();

		// lockTest4();

		// lockTest7();

		// lockTest10();

		// lockTest12();

		// lockTest13();

		// lockTest14();

		// lockTest15();

		fileTryLockTest16();

	}

	private static void fileTryLockTest16() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			System.out.println("2 before tryLock");
			FileLock fileLock = channel.tryLock(0, Integer.MAX_VALUE, true);
			System.out.println("2 after tryLock lock = " + fileLock);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest15() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_2.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, false);
			System.out.println("2 get lock");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest14() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_2.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, true);
			System.out.println("2 get lock");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest13() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_2.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, true);
			System.out.println("2 get lock");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest12() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_2.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, false);
			System.out.println("2 get lock");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest10() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.write(ByteBuffer.wrap("11111111".getBytes()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest7() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.write(ByteBuffer.wrap("world".getBytes()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest4() {
		try(FileChannel channel = new FileOutputStream(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath()).getChannel()) {


			Thread lockThread2 = new Thread(() -> {
				try {
					System.out.println("lockThread2 before lock");
					channel.lock(1, 2, false); // 与lockThread一样获取
					System.out.println("lockThread2 has get lock");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			lockThread2.start();
			TimeUnit.SECONDS.sleep(1);

			lockThread2.interrupt(); // 这里应该是lockThread2处于阻塞状态，等待获取fileLock，这里调用interrupt之后，会抛出异常

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest1() {
		try(FileChannel fileChannel = new RandomAccessFile(new File(_13_FileChannel_FileLock_1.class.getClassLoader().getResource("channel/_13_FileChannel_fileLock").getPath()),"rw").getChannel()) {

			System.out.println("2_1 begin");
			fileChannel.lock(1, 2, false);
			System.out.println("2_2 end");

			TimeUnit.SECONDS.sleep(30);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
