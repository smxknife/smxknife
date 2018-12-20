package com.smxknife.netty.v5.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2018-11-30
 */
public class AsyncTimeClientHandler implements Runnable, CompletionHandler<Void, AsyncTimeClientHandler> {

	int port;
	String ip;
	AsynchronousSocketChannel asynchronousSocketChannel;
	CountDownLatch latch;

	public AsyncTimeClientHandler(int port, String ip) throws IOException {
		this.ip = ip;
		this.port = port;
		asynchronousSocketChannel = AsynchronousSocketChannel.open();
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		asynchronousSocketChannel.connect(new InetSocketAddress(ip, port), this, this);

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			asynchronousSocketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void completed(Void result, AsyncTimeClientHandler attachment) {
		String msg = "Client send msg: " + LocalDateTime.now().toString();
		byte[] bytes = msg.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		asynchronousSocketChannel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (attachment.hasRemaining()) {
					asynchronousSocketChannel.write(byteBuffer, byteBuffer, this);
				} else {
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							attachment.flip();
							byte[] readBytes = new byte[attachment.remaining()];
							attachment.get(readBytes);
							String response = new String(readBytes);
							System.out.println("client receive response : " + response);
							latch.countDown();
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							close();
						}
					});
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				close();
			}
		});
	}

	@Override
	public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
		this.close();
	}

	private void close() {
		try {
			asynchronousSocketChannel.close();
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
