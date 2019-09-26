package com.smxknife.xnio.demo01;

import org.xnio.IoFuture;
import org.xnio.OptionMap;
import org.xnio.Xnio;
import org.xnio.XnioWorker;
import org.xnio.channels.Channels;
import org.xnio.channels.ConnectedStreamChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author smxknife
 * 2019/9/9
 */
public class SimpleClient {
	public static void main(String[] args) throws IOException {
		final Charset charset = Charset.forName("utf-8");
		// 创建xnio实例，并构造XnioWorker
		final Xnio xnio = Xnio.getInstance();
		final XnioWorker worker = xnio.createWorker(OptionMap.EMPTY);

		// 连接服务器，本地12345端口，返回值是IOFuture类型，并不阻塞，返回后可以做别的事
		IoFuture<ConnectedStreamChannel> futureConnection =
				worker.connectStream(new InetSocketAddress("localhost", 12345), null, OptionMap.EMPTY);
		System.out.println("打个断点便于调试");
		// get是阻塞调用
		try(final ConnectedStreamChannel channel = futureConnection.get()) {

			// 发送消息
			Channels.writeBlocking(channel, ByteBuffer.wrap("Hello World\n".getBytes(charset)));
			// 保证全部送出
			Channels.flushBlocking(channel);
			// 发送EOF
			channel.shutdownWrites();
			System.out.println("[ " + Thread.currentThread().getName() + " ] Send greeting string! The response is...");
			ByteBuffer recvBuf = ByteBuffer.allocate(128);
			// 接收消息
			while (Channels.readBlocking(channel, recvBuf) != -1) {
				recvBuf.flip();
				final CharBuffer chars = charset.decode(recvBuf);
				System.out.print(chars);
				recvBuf.clear();
			}
		}

		worker.shutdown();
	}
}
