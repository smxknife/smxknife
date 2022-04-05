package com.smxknife.java2.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _13_FileChannel_FileLock_1 {
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
		// lockTest1();

		// 验证AsynchronousCloseException异常的发生
		// lockTest2();

		// 验证FileLockInterruptionException异常发生，线程在获取锁时被interrupt
		// lockTest3();

		// 验证FileLockInterruptionException异常发生，两个线程间; 需要FileLock_2的lockTest4配合
		// 这里注意，必须使用两个main，也就是两个进程，如果在同一个进程中启动两个线程，那么抛出的异常是OverlappingFileinterruptionException
		// 其实fileLock是进程锁，不适用多线程，但是对于多线程会抛出上面的异常，测试见lockTest5
		// lockTest4();

		// 验证同一个进程，不同线程调用interrupt，即lockTest4的注意测试
		// lockTest5();

		// 验证共享锁自己不能写（出现异常）
		// TODO 很遗憾这里竟然可写，测试系统是Mac
		lockTest6();

		// 验证共享锁别人不能写（出现异常），同样需要借助FileLock_2的同名方法来测试
		// TODO 很遗憾同样不会抛出异常，测试系统是Mac和Centos7，抱歉当前家里没有windows测试环境
		// lockTest7();

		// 验证共享锁自己能读
		// lockTest8();

		// 验证共享锁别人能读（这里就不验证了，由于当前的mac和linux系统写都可写，读基本不成问题）

		// 验证独占锁自己能写
		// TODO mac是没有问题的，windows没有测试
		// lockTest9();

		// 验证独占锁别人不能写，这里需要借助FileLock_2的同名方法来测试
		// TODO 很遗憾，mac测试时无效，其他系统也可以写
		// lockTest10();

		// 验证独占锁自己能读（这里不做验证）

		// 验证独占锁别人能读（TODO 这里也不做验证，但是如果有windows系统最好验证一下）

		// 验证lock方法position和size的含义
		// 这一部分是为了说明未锁定的部分可以写，但是已锁定的部分是不可以写的
		// TODO 但是在目前的mac测试中，测试不通过，无论是否在锁定区域都是可以写
		// lockTest11();

		// 验证提前锁定，目的就是设置的position比现有的文件大小还大，那么在写入的时候，可以写入部分数据，当到达限制后，才被锁定
		// TODO 不做试验了，mac无法处理

		// TODO ... 想办法借用了一台windows电脑，安装jdk8的环境进行测试，竟然上面的条件都ok，那么严重说明FileLock与操作系统有关
		// TODO ... 所以在实际使用过程中，要绝对严谨的使用，尽量减少使用，避免因为操作系统的差异而导致这种bug

		// 验证独占锁与独占锁是互斥的，（考虑到mac系统的特殊性，只有这个通过测试，其他的共享锁非互斥才具有可测试性）
		// 经过测试，即使是在mac系统上也能测试通过
		// lockTest12();

		// 验证独占锁与共享锁是互斥的，借助同名函数
		// lockTest13();

		// 验证共享锁与共享锁是非互斥，借助同名函数
		// lockTest14();

		// 验证共享锁与独占锁是互斥的，借助同名函数
		// lockTest15();

		// tryLock与lock的区别就是，前者非阻塞，后者阻塞，借助于同名函数
		// 对应2里面的同名函数，采用tryLock后没有像lock一样阻塞，而是运行完，只是返回的FileLock为null
		// fileTryLockTest16();

		// ********************************************************************
		// ********************************************************************
		// ********************************************************************

		/**
		 * 概念：
		 *      FileLock表示文件区域锁定的标记，每次通过lock或tryLock方法获取文件上的锁定时，就会创建一个FileLock对象
		 * 有效性：
		 *      lock或tryLock成功获取FileLock对象开始
		 *      release调用、关闭通道、终止虚拟机任一操作之前
		 *      上面两个阶段之间，FileLock都是有效的，可以通过FileLock对象的isValid来测试锁定特性
		 * 特点：
		 *      单个虚拟机上的锁定是不可以重叠的，可以通过overlap来测试是否重叠锁定
		 *      FileLock对象只有是否有效是随着时间变化的，其他都是不变的，包括保持锁定的文件通道、锁定类型、锁定区域和大小
		 *      文件锁定是以整个虚拟机来保持的。但不适用于同一个虚拟机内多个线程对文件的访问
		 *      多个并发线程可以安全的使用FileLock对象
		 *      FileLock类具有平台依赖性，此文件锁定API直接映射到底层的操作系统的本机锁定机制。因为程序无论用何种语言编写的，某个文件上所保持的锁定对所有访问该文件的程序是可见的
		 */

		// FileLock API测试
		// fileLockTest17();

		// overlaps测试
		// fileLockTest18();



	}

	private static void fileLockTest18() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			FileLock fileLock = channel.lock(1, 10, true);
			System.out.println(fileLock.size());
			System.out.println(fileLock.overlaps(1, 10));
			System.out.println(fileLock.overlaps(8, 13));
			System.out.println(fileLock.overlaps(11, 12));
			System.out.println("------------");
			System.out.println(fileLock.overlaps(0, 1));
			System.out.println(fileLock.overlaps(1, 1));
			System.out.println(fileLock.overlaps(10, 1));
			System.out.println(fileLock.overlaps(11, 1));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void fileLockTest17() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			System.out.printf("channel hashCode = %s\r\n", channel.hashCode());
			FileLock fileLock = channel.lock(1, 10, true);
			System.out.printf("A lock position = %s, size = %s, isValid = %s, isShared = %s, channel hashCode = %s, acquiredBy hashCode = %s\r\n",
					fileLock.position(), fileLock.size(), fileLock.isValid(), fileLock.isShared(), fileLock.channel().hashCode(), fileLock.acquiredBy().hashCode());
			fileLock.release();

			fileLock = channel.lock(1, 10, false);
			System.out.printf("B lock position = %s, size = %s, isValid = %s, isShared = %s, channel hashCode = %s, acquiredBy hashCode = %s\r\n",
					fileLock.position(), fileLock.size(), fileLock.isValid(), fileLock.isShared(), fileLock.channel().hashCode(), fileLock.acquiredBy().hashCode());
			fileLock.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void fileTryLockTest16() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			System.out.println("1 before tryLock");
			FileLock fileLock = channel.tryLock(0, Integer.MAX_VALUE, false);
			System.out.println("1 after tryLock fileLock = " + fileLock);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest15() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, true);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest14() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, true);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest13() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, false);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest12() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, Integer.MAX_VALUE, false);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest11() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			System.out.println("A channel position = " + channel.position());
			channel.lock(3, 2, true); // 这里最好使用true，虽然对于mac没区别
			System.out.println("B channel position = " + channel.position());
			channel.write(ByteBuffer.wrap("0".getBytes())); // index=0;
			System.out.println("C channel position = " + channel.position());

			channel.write(ByteBuffer.wrap("1".getBytes())); // index=1;
			System.out.println("D channel position = " + channel.position());
			channel.write(ByteBuffer.wrap("2".getBytes())); // index=2;
			System.out.println("E channel position = " + channel.position());
			channel.write(ByteBuffer.wrap("3".getBytes())); // index=3;
			System.out.println("F channel position = " + channel.position());
			channel.write(ByteBuffer.wrap("4".getBytes())); // index=4;
			System.out.println("G channel position = " + channel.position());




		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest10() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(1, 2, false);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest9() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(1, 2, false);
			channel.write(ByteBuffer.wrap("123456".getBytes()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest8() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(1, 2, true);
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			channel.read(byteBuffer);
			byteBuffer.rewind();
			System.out.println(new String(byteBuffer.array()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest7() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(1, 2, true);
			TimeUnit.HOURS.sleep(1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest6() {
		try(FileChannel channel = new RandomAccessFile(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath(), "rw").getChannel()) {

			channel.lock(0, 2, true);
			System.out.printf("channel position %s, size %s", channel.position(), channel.size());
			channel.write(ByteBuffer.wrap("hello".getBytes()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest5() {
		try(FileChannel channel = new FileOutputStream(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath()).getChannel()) {

			Thread lockThread1 = new Thread(() -> {
				try {
					System.out.println("lockThread1 before lock");
					channel.lock(1, 2, false);
					System.out.println("lockThread1 has get lock");
					TimeUnit.SECONDS.sleep(3); // 这里是为了让线程多待一会，防止线程执行完了，再关闭就没有效果了
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			});

			Thread lockThread2 = new Thread(() -> {
				try {
					System.out.println("lockThread2 before lock");
					channel.lock(1, 2, false); // 与lockThread一样获取
					System.out.println("lockThread2 has get lock");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			lockThread1.start();
			TimeUnit.MICROSECONDS.sleep(100);

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

	private static void lockTest4() {
		try(FileChannel channel = new FileOutputStream(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath()).getChannel()) {

			System.out.println("lockThread1 before lock");
			channel.lock(1, 2, false);
			System.out.println("lockThread1 has get lock");
			TimeUnit.SECONDS.sleep(30); // 这里是为了让线程多待一会，防止线程执行完了，再关闭就没有效果了

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest3() {
		try(FileChannel channel = new FileOutputStream(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath()).getChannel()) {

			Thread lockThread1 = new Thread(() -> {
				try {
					for (int i = 0; i < 1000000; i++) {
						long anInt = ThreadLocalRandom.current().nextInt(i + 1);
						if (i % 50000 == 0) {
							System.out.println(i);
						}
					}
					System.out.println("lockThread1 before lock");
					channel.lock(1, 2, false);
					System.out.println("lockThread1 has get lock");

					System.out.println("lockThread1 finish");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			lockThread1.start();
			TimeUnit.MICROSECONDS.sleep(500);

			lockThread1.interrupt();

			TimeUnit.SECONDS.sleep(10);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void lockTest2() {
		try(FileChannel channel = new FileOutputStream(_13_FileChannel_FileLock_1.class.getClassLoader()
				.getResource("channel/_13_FileChannel_fileLock").getPath()).getChannel()) {

			Thread lockThread = new Thread(() -> {
				try {
					channel.lock(1, 2, false);
					TimeUnit.SECONDS.sleep(1); // 这里是为了让线程多待一会，防止线程执行完了，再关闭就没有效果了
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			});

			Thread channelCloseThread = new Thread(() -> {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			lockThread.start();
			TimeUnit.MICROSECONDS.sleep(100);

			channelCloseThread.start();
			TimeUnit.SECONDS.sleep(1);

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

			System.out.println("1_1 begin");
			fileChannel.lock(1, 2, false);
			System.out.println("1_2 end");

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
