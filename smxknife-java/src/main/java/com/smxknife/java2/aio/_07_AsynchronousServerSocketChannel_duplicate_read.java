package com.smxknife.java2.aio;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/11/4
 */
public class _07_AsynchronousServerSocketChannel_duplicate_read {

	@Test
	public void testServerThrowsReadPendingException() {
		try(AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open()) {
			channel.setOption(StandardSocketOptions.SO_RCVBUF, 1024)
					.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			channel.bind(new InetSocketAddress(8080), 10);
			channel.accept("server", new CompletionHandler<AsynchronousSocketChannel, String>() {
				@Override
				public void completed(AsynchronousSocketChannel result, String attachment) {
					System.out.println(attachment + " accept success | " + result.toString());
					channel.accept(attachment, this);

					ByteBuffer byteBuffer = ByteBuffer.allocate(20);
					Future<Integer> future = result.read(byteBuffer);
					ByteBuffer byteBuffer2 = ByteBuffer.allocate(20);
					Future<Integer> future2 = result.read(byteBuffer2);
//					try {
//						System.out.println(new String(byteBuffer.array(), 0, future.get()) );
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					} catch (ExecutionException e) {
//						e.printStackTrace();
//					}
				}

				@Override
				public void failed(Throwable exc, String attachment) {
					System.out.println("fail");
				}
			});
			TimeUnit.SECONDS.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testServerWithFutureGetNoException() {
		try(AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open()) {
			channel.setOption(StandardSocketOptions.SO_RCVBUF, 1024)
					.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			channel.bind(new InetSocketAddress(8080), 10);
			channel.accept("server", new CompletionHandler<AsynchronousSocketChannel, String>() {
				@SneakyThrows
				@Override
				public void completed(AsynchronousSocketChannel result, String attachment) {
					System.out.println(attachment + " accept success | " + result.toString());
					channel.accept(attachment, this);

					ByteBuffer byteBuffer = ByteBuffer.allocate(20);
					Future<Integer> future = result.read(byteBuffer);
					future.get();
					System.out.println("read1 finish");
					ByteBuffer byteBuffer2 = ByteBuffer.allocate(20);
					Future<Integer> future2 = result.read(byteBuffer2);
					future2.get();
					System.out.println("read2 finish");
//					try {
//						System.out.println(new String(byteBuffer.array(), 0, future.get()) );
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					} catch (ExecutionException e) {
//						e.printStackTrace();
//					}
				}

				@Override
				public void failed(Throwable exc, String attachment) {
					System.out.println("fail");
				}
			});
			TimeUnit.SECONDS.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testClient() {
		try(AsynchronousSocketChannel channel = AsynchronousSocketChannel.open()) {
			channel.setOption(StandardSocketOptions.SO_SNDBUF, 100)
					.setOption(StandardSocketOptions.SO_RCVBUF, 100)
					.setOption(StandardSocketOptions.SO_KEEPALIVE, false)
					.setOption(StandardSocketOptions.SO_REUSEADDR, true)
					.setOption(StandardSocketOptions.TCP_NODELAY, true);
					//.setOption(StandardSocketOptions.SO_LINGER, 5) // unsupport
					//.setOption(StandardSocketOptions.IP_TOS, 1) // unsupport

			channel.connect(new InetSocketAddress(8080), "client1", new CompletionHandler<Void, String>() {
				@Override
				public void completed(Void result, String attachment) {
					System.out.println(attachment + " connect success");
					Future<Integer> future = channel.write(ByteBuffer.wrap((attachment + ": hello server").getBytes()));
					try {
						System.out.println("写入大小：" + future.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void failed(Throwable exc, String attachment) {
					System.out.println(attachment + " connect fail");
				}
			});

			TimeUnit.SECONDS.sleep(10);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
