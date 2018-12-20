package com.smxknife.netty.v5.demo09;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * @author smxknife
 * 2018-12-09
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private final String url;

	public HttpFileServerHandler(String url) {
		this.url = url;
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

		if (!request.getDecoderResult().isSuccess()) {
			sendError(ctx, HttpResponseStatus.BAD_REQUEST);
			return;
		}
		if (request.getMethod() != HttpMethod.GET) {
			sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
			return;
		}

		final String uri = request.getUri();
		System.out.println("request uri = " + uri);
		final String path = sanitizeUri(uri);
		if (path == null) {
			sendError(ctx, HttpResponseStatus.FORBIDDEN);
			return;
		}
		File file = new File(path);
		if (file.isHidden() || !file.exists()) {
			sendError(ctx, HttpResponseStatus.NOT_FOUND);
			return;
		}
		if (file.isDirectory()) {
			if (uri.endsWith("/")) {
				sendListing(ctx, file);
			} else {
				sendRedirect(ctx, uri + "/");
			}
			return;
		}
		if (!file.isFile()) {
			sendError(ctx, HttpResponseStatus.FORBIDDEN);
			return;
		}
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			sendError(ctx, HttpResponseStatus.NOT_FOUND);
			return;
		}
		long fileLength = randomAccessFile.length();
		HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		setContentLength(response, fileLength);
		setContentTypeHeader(response, file);

		if (HttpHeaders.isKeepAlive(request)) {
			response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
		}
		ctx.write(response);
		ChannelFuture channelFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise());
		channelFuture.addListener(new ChannelProgressiveFutureListener() {
			@Override
			public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
				if (total < 0) {
					System.err.println("Transfer progress: " + progress);
				} else {
					System.err.println("Transfer progress: " + progress + "/" + total);
				}
			}

			@Override
			public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
				System.out.println("Transfer complete.");
			}
		});

		ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
		if (!HttpHeaders.isKeepAlive(request)) {
			lastContentFuture.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if (ctx.channel().isActive()) {
			sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			ctx.close();
		}
	}

	private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
	private static final Pattern ALLOW_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

	private void sendRedirect(ChannelHandlerContext ctx, String newUrl) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
		response.headers().set(HttpHeaders.Names.LOCATION, newUrl);
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private String sanitizeUri(String uri) {
		try {
			uri = URLDecoder.decode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			try {
				URLDecoder.decode(uri, "ISO-8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.addSuppressed(e);
				e1.printStackTrace();
			}
		}
		if (!uri.startsWith(url)) return null;
		if (!uri.startsWith("/")) return null;
		uri = uri.replace('/', File.separatorChar);
		System.out.println("file separatorChar : " + File.separatorChar);
		System.out.println("file pathSeparator : " + File.pathSeparator);
		System.out.println("file separator : " + File.separator);

		if (uri.contains(File.separator + '.')
			|| uri.contains('.' + File.separator)
			|| uri.startsWith(".")
			|| uri.endsWith(".")
			|| INSECURE_URI.matcher(uri).matches()) {
			return null;
		}
		System.out.println("user.dir = " + System.getProperty("user.dir") + File.separator + uri);
		return System.getProperty("user.dir") + File.separator + uri;
	}

	private void sendListing(ChannelHandlerContext ctx, File dir) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html;charset=UTF-8");
		StringBuilder builder = new StringBuilder();
		String dirPath = dir.getPath();
		builder.append("<!DOCTYPE html>\r\n")
				.append("<html><head><title>")
				.append(dirPath)
				.append(" 目录： ")
				.append("</title></head><body>\r\n")
				.append("<h3>")
				.append(dirPath)
				.append(" 目录： ")
				.append("</h3>\r\n")
				.append("<ul>")
				.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
		for (File f : dir.listFiles()) {
			if (f.isHidden() || !f.canRead()) {
				continue;
			}
			String name = f.getName();
			if (!ALLOW_FILE_NAME.matcher(name).matches()) {
				continue;
			}
			builder.append("<li>链接：<a href=\"")
					.append(name)
					.append("\">")
					.append(name)
					.append("</a></li>\r\n");
		}

		builder.append("</ul></body></html>\r\n");
		ByteBuf byteBuf = Unpooled.copiedBuffer(builder, CharsetUtil.UTF_8);
		response.content().writeBytes(byteBuf);
		byteBuf.release();
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1,
				status,
				Unpooled.copiedBuffer("Failure:  " + status.toString() + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private void setContentTypeHeader(HttpResponse response, File file) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, mimeTypesMap.getContentType(file));
	}
}
