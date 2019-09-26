package com.smxknife.xnio.demo01;

import org.xnio.*;
import org.xnio.channels.AcceptingChannel;
import org.xnio.channels.Channels;
import org.xnio.channels.ConnectedStreamChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2019/9/9
 */
public class SimpleEchoServer {
	public static void main(String[] args) throws IOException {

		// 定义读数据listener
		ChannelListener<ConnectedStreamChannel> channelReadListener =
				new ChannelListener<ConnectedStreamChannel>() {

					@Override
					public void handleEvent(ConnectedStreamChannel connectedStreamChannel) {
						// 分配缓冲
						final ByteBuffer buffer = ByteBuffer.allocate(512);
						int res;
						try {
							while ((res = connectedStreamChannel.read(buffer)) > 0) {
								buffer.flip();
								Channels.writeBlocking(connectedStreamChannel, buffer);
							}

							// 保证全部送出去
							Channels.flushBlocking(connectedStreamChannel);

							if (res == -1) {
								connectedStreamChannel.close();
							} else {
								connectedStreamChannel.resumeReads();
							}

						} catch (IOException e) {
							e.printStackTrace();
							IoUtils.safeClose(connectedStreamChannel);
						}
					}
				};

		// 创建接收listener
		ChannelListener<AcceptingChannel<ConnectedStreamChannel>> channelAcceptListener = new ChannelListener<AcceptingChannel<ConnectedStreamChannel>>() {

			@Override
			public void handleEvent(AcceptingChannel<ConnectedStreamChannel> connectedStreamChannelAcceptingChannel) {
				try {
					ConnectedStreamChannel accepted;
					// channel就绪，准备接收连接请求
					while ((accepted = connectedStreamChannelAcceptingChannel.accept()) != null) {
						System.out.println("accepted " + accepted.getPeerAddress());
						// 已连接设置读取数据listener
						accepted.getReadSetter().set(channelReadListener);
						// 恢复读的状态
						accepted.resumeReads();
					}
				} catch (IOException e) {

				}
			}
		};

		// 创建xnio实例，并构造XnioWorkder
		final XnioWorker worker = Xnio.getInstance().createWorker(OptionMap.EMPTY);
		// 创建server，本地监听12345端口
		AcceptingChannel<? extends ConnectedStreamChannel> streamServer = worker.createStreamServer(new InetSocketAddress(12345), channelAcceptListener, OptionMap.EMPTY);
		streamServer.resumeAccepts();

		System.out.println("Listening on " + streamServer.getLocalAddress());

	}
}
