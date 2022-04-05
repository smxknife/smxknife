package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _05_ServerSocketChannel_Accept_ByteBuffer {

	@Test
	public void acceptByteBufferServerTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

			serverSocketChannel.bind(new InetSocketAddress(6666));
			System.out.println("before accept " + System.currentTimeMillis());
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("after accept " + System.currentTimeMillis());

			ByteBuffer byteBuffer = ByteBuffer.allocate(3); // 每个汉字占3个字节
			int readLength = 0;
			while ((readLength = socketChannel.read(byteBuffer)) != -1) {
				// byteBuffer.flip();
				// String string = StandardCharsets.UTF_16LE.decode(byteBuffer).toString();
				String string = new String(byteBuffer.array());
				System.out.println(string + " | readLenght = " + readLength);
				byteBuffer.clear();
			}

			socketChannel.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void acceptByteBufferClientTest() {
		try(Socket socket = new Socket("localhost", 6666);
		    OutputStream outputStream = socket.getOutputStream();) {

			outputStream.write("客户端测试发送数据".getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
