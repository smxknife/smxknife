package com.smxknife.java2.aio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/11/3
 */
public class _05_AsynchronousFileChannel_completionHandler {

	@Test
	public void test() {
		Path path = Paths.get(this.getClass().getClassLoader().getResource("aio/_01_lock").getPath());
		try(AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
			channel.lock("我是附加值", new CompletionHandler<FileLock, String>() {
				@Override
				public void completed(FileLock result, String attachment) {
					System.out.println("A 加锁成功：FileLock = " + result.toString() + " | attachment = " + attachment);
					try {
						result.release();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void failed(Throwable exc, String attachment) {
					System.out.println("A 加锁失败，throwable = " + exc.getMessage() + " | attachment = " + attachment);
				}
			});

			TimeUnit.SECONDS.sleep(10); // 注意这里是为了让程序慢一点执行，否则主程序执行过快，会导致还没有加锁成功程序就运行完毕
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}


}
