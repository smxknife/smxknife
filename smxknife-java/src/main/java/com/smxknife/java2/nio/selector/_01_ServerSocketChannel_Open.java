package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _01_ServerSocketChannel_Open {

	// 仅仅作为使用demo来看看
	@Test
	public void demo1Test() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    ServerSocket serverSocket = serverSocketChannel.socket();) {

			// 将ServerSocket绑定到指定地址，也可以使用ServerSocketChannel的bind方法，见demo2Test
			serverSocket.bind(new InetSocketAddress("localhost", 6666));
			System.out.println(".....before accept......");
			Socket socket = serverSocket.accept();
			System.out.println(".....after accept......");

			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			char[] chars = new char[1024];
			int readLength = 0;
			while ((readLength = inputStreamReader.read(chars)) != -1) {
				System.out.println(new String(chars, 0, readLength));
			}
			inputStreamReader.close();
			inputStream.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 测试open状态
	@Test
	public void isOpenTest() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			System.out.println(serverSocketChannel.isOpen());
			serverSocketChannel.close();
			System.out.println(serverSocketChannel.isOpen());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
