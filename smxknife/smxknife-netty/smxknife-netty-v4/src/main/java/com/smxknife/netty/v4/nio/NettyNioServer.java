package com.smxknife.netty.v4.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author smxknife
 * 2020/3/17
 */
public class NettyNioServer {
	public void server(int port) throws InterruptedException {
		final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));

		EventLoopGroup group = new NioEventLoopGroup(); // TODO 与OIO的区别1
		try {
			ServerBootstrap bootstrap = new ServerBootstrap()
					.group(group)
					.channel(NioServerSocketChannel.class) // TODO 与OIO的区别2
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
								@Override
								public void channelActive(ChannelHandlerContext ctx) throws Exception {
									ctx.writeAndFlush(buf.duplicate())
											.addListener(ChannelFutureListener.CLOSE);
								}
							});
						}
					});
			ChannelFuture future = bootstrap.bind().sync();
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}

	}
}
