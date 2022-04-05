package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/7
 */
public class _03_ServerSocket {

	@Test
	public void serverSocketTest() {
		try {
			ServerSocket socket = new ServerSocket(8088);
			System.out.println("server阻塞开始= " + System.currentTimeMillis());
			socket.accept();
			System.out.println("server阻塞结束= " + System.currentTimeMillis());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void clientConnectToServerTest() {
		System.out.println("client连接准备=" + System.currentTimeMillis());
		try {
			Socket socket = new Socket("localhost", 8088);
			System.out.println("client连接结束=" + System.currentTimeMillis());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void webServerTest() throws IOException {
		ServerSocket serverSocket = new ServerSocket(6666);
		Socket socket = serverSocket.accept();
		System.out.println("接收到连接，port = " + socket.getPort());
		InputStream inputStream = socket.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(streamReader);

		String getString = "";
		while (!"".equals(getString = bufferedReader.readLine())) {
			System.out.println(getString);
		}

		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
		outputStream.write("<html><body><a href='http://www.baidu.com'>I am baidu.com welcome you!</a></body></html>".getBytes());
		outputStream.flush();
		outputStream.close();
		socket.close();
		serverSocket.close();
	}

	@Test
	public void inputStreamReadBlockTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();) {
			System.out.println(inputStream.getClass().getName());
			System.out.println("read begin " + System.currentTimeMillis());
			byte[] bytes = new byte[inputStream.available()];
			int readLength = inputStream.read(bytes);
			System.out.println("read end " + System.currentTimeMillis());
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inputStreamReadBlockClientTest() {
		System.out.println("client socket begin " + System.currentTimeMillis());
		try(Socket socket = new Socket("localhost", 6666)) {
			System.out.println("client socket end " + System.currentTimeMillis());
			TimeUnit.SECONDS.sleep(30);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void acceptTimeoutTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666);) {
			serverSocket.setSoTimeout(5000);
			Socket socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void backlogServerTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666, 3)) {
			TimeUnit.SECONDS.sleep(30); // 这里睡眠30s是为了先不让serversocket进行accept，先让客户端连接
			while (true) {
				Socket socket = serverSocket.accept();
				int port = socket.getPort();
				System.out.println("client port = " + port + "接入！");
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void backlogClientTest() {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println("线程 " + Thread.currentThread().getName() + " 准备ok...");
				Socket socket = null;
				try {
					socket = new Socket("localhost", 6666);
					System.out.println("client 连接成功 " + socket.getLocalPort() + " | thread = " + Thread.currentThread().getName());
					TimeUnit.SECONDS.sleep(30);
					System.out.println("client port = " + socket.getLocalPort() + " finished");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " 连接失败");
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}, "th-" + i).start();
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
