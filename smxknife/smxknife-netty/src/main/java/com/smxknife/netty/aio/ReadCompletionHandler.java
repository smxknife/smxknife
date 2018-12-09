package com.smxknife.netty.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2018-11-30
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	private AsynchronousSocketChannel asynchronousSocketChannel;

	public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
		if (this.asynchronousSocketChannel == null) {
			this.asynchronousSocketChannel = asynchronousSocketChannel;
		}
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] bytes = new byte[attachment.remaining()];
		attachment.get(bytes);
		String string = new String(bytes);
		System.out.println("TimeServer receive : " + string);
		doWrite("<Receive> " + LocalDateTime.now().toString());
	}

	private void doWrite(String resp) {
		byte[] bytes = resp.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();

		asynchronousSocketChannel.write(byteBuffer, byteBuffer,
				new CompletionHandler<Integer, ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						// 如果没有发送完成继续发送
						if (attachment.hasRemaining()) {
							asynchronousSocketChannel.write(byteBuffer, byteBuffer, this);
						}
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						try {
							asynchronousSocketChannel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			this.asynchronousSocketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
