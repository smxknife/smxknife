package com.smxknife.netty.demo11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * @author smxknife
 * 2018-12-10
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());

	private WebSocketServerHandshaker handshaker;

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		// 传统的HTTP接入
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
		}
		// WebSocket接入
		else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(channelHandlerContext, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private void handleWebSocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
		// 判断是否是关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(channelHandlerContext.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		// 判断是否是ping的消息
		if (frame instanceof PingWebSocketFrame) {
			channelHandlerContext.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		// 仅文本消息处理
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
		}
		// 返回应答消息
		String request = ((TextWebSocketFrame) frame).text();
		if (logger.isLoggable(Level.FINE)) {
			logger.fine(String.format("%s received %s", channelHandlerContext.channel(), request));
		}
		channelHandlerContext.channel().write(new TextWebSocketFrame(request + " , 欢迎使用Netty WebSocket服务，现在时刻：" + LocalDateTime.now().toString()));
	}

	private void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) {
		// 如果HTTP解码失败，返回HTTP异常
		if (!request.getDecoderResult().isSuccess()
				|| !"websocket".equals(request.headers().get("Upgrade"))) {
			sendHttpResponse(channelHandlerContext, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}

		// 构造握手响应返回，本机测试
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
		handshaker = wsFactory.newHandshaker(request);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(channelHandlerContext.channel());
		} else {
			handshaker.handshake(channelHandlerContext.channel(), request);
		}
	}

	private void sendHttpResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest request, FullHttpResponse response) {
		// 返回应答给客户端
		if (response.getStatus().code() != 200) {
			ByteBuf byteBuf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
			response.content().writeBytes(byteBuf);
			byteBuf.release();
			setContentLength(request, response.content().readableBytes());
		}
		// 如果是非Keep-Alive，关闭连接
		ChannelFuture future = channelHandlerContext.channel().writeAndFlush(response);
		if (!isKeepAlive(request) || response.getStatus().code() != 200) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
}
