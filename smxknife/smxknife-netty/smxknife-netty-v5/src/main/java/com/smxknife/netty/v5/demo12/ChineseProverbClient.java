package com.smxknife.netty.v5.demo12;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author smxknife
 * 2018-12-10
 */
public class ChineseProverbClient {
	public void run(int port) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new ChineseProverbClientHandler());

			Channel channel = bootstrap.bind(0).sync().channel();
			// 向网段内所有的机器广播UDP消息
			channel.writeAndFlush(
					new DatagramPacket(
							Unpooled.copiedBuffer("谚语", CharsetUtil.UTF_8),
							new InetSocketAddress("255.255.255.255", port)))
					.sync();
			if (!channel.closeFuture().await(15000)) {
				System.out.println("查询超时");
			}
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new ChineseProverbClient().run(8080);
	}
}
