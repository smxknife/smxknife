package com.smxknife.java2.aio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/11/3
 */
public class _02_AsynchronousFileChannel_region_lock {

	/**
	 * 获取文件的区域独占锁public final Future<FileLock> lock(position, size, shared);
	 * Future的get方法在成功完成时返回FileLock
	 */
	@Test
	public void testLockA() {
		Path path = Paths.get(this.getClass().getClassLoader().getResource("aio/_01_lock").getPath());
		try(AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
			Future<FileLock> lockFuture = channel.lock(0, 3, false);
			FileLock fileLock = lockFuture.get();
			System.out.println("A get lock time " + LocalTime.now());
			TimeUnit.SECONDS.sleep(30);
			fileLock.release();
			System.out.println("A release lock time " + LocalTime.now());
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLockB() {
		Path path = Paths.get(this.getClass().getClassLoader().getResource("aio/_01_lock").getPath());
		try(AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
			System.out.println("B get lock begin " + LocalTime.now());
			Future<FileLock> lockFuture = channel.lock(0, 3, false);
			// 下面的输出紧接着begin输出，说明调用lock方法立刻返回，不会产生阻塞
			System.out.println("B get lock end " + LocalTime.now());
			FileLock fileLock = lockFuture.get();
			// 这里的get lock time 会一直阻塞，等待testLockA中的释放锁之后才会继续执行
			System.out.println("B get lock time " + LocalTime.now());
			fileLock.release();
			System.out.println("B release lock time " + LocalTime.now());
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLockC() {
		Path path = Paths.get(this.getClass().getClassLoader().getResource("aio/_01_lock").getPath());
		try(AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
			System.out.println("C get lock begin " + LocalTime.now());
			Future<FileLock> lockFuture = channel.lock(4, 8, false);
			// 下面的输出紧接着begin输出，说明调用lock方法立刻返回，不会产生阻塞
			System.out.println("C get lock end " + LocalTime.now());
			FileLock fileLock = lockFuture.get();
			// 这里的get lock time 会一直阻塞，等待testLockA中的释放锁之后才会继续执行
			System.out.println("C get lock time " + LocalTime.now());
			fileLock.release();
			System.out.println("C release lock time " + LocalTime.now());
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
