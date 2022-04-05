package com.smxknife.game.hero;

import com.smxknife.game.hero.handler.cmd.CmdHandlerRepository;
import com.smxknife.game.hero.handler.result.ResultHandlerRepository;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.log4j.Log4j2;

/**
 * @author smxknife
 * 2021/5/5
 */
@Log4j2
public class ServerMain {

	public static void main(String[] args) {

		final ServerContext serverContext = new ServerContext();
		final CmdHandlerRepository cmdHandlerRepository = new CmdHandlerRepository(serverContext);
		final ResultHandlerRepository resultHandlerRepository = new ResultHandlerRepository(serverContext);

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						final ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast(
								new HttpServerCodec(),
								new HttpObjectAggregator(65535),
								new WebSocketServerProtocolHandler("/websocket"),
								new GameMsgDecoder(cmdHandlerRepository), // 自定义解码器
								new GameMsgEncoder(resultHandlerRepository), // 自定义消息编码器
								new GameMessageHandler(cmdHandlerRepository));
					}
				});
		try {
			final ChannelFuture bindSyncChannelFuture = serverBootstrap.bind(12345).sync();

			if (bindSyncChannelFuture.isSuccess()) {
				log.info("服务已启动");
			}

			bindSyncChannelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
