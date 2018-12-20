package com.smxknife.netty.v5.demo13;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 客户端
 * @author smxknife
 * 2018-12-12
 */
public class NettyClient {
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) throws InterruptedException {
		System.out.println("host : " + host + ", port : " + port);
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline()
									.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0).setName("client"))
									.addLast("MessageEncoder", new NettyMessageEncoder().setName("client"))
									.addLast("readTimeoutHandler", new ReadTimeoutHandler(50))
									.addLast("LoginAuthHandler", new LoginAuthReqHandler())
									.addLast("HeartBeatHandler", new HeartBeatReqHandler());
						}
					});
//			ChannelFuture future = bootstrap.connect(
//					new InetSocketAddress(host, port),
//					new InetSocketAddress(NettyConstant.LOCAL_IP, NettyConstant.LOCAL_PORT)
//			).sync();
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			// 所有资源释放完后，清空资源，再次重新发起连接
			executor.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5);
					connect(port, host); // 发起重新连接操作
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new NettyClient().connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTE_IP);
	}
}
