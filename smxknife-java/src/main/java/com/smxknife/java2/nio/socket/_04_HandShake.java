package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/8
 */
public class _04_HandShake {

	// 三次握手建立服务端
	@Test
	public void serverTest1() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
			serverSocket.accept();
			System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 三次握手建立客户端
	@Test
	public void clientTest1() {
		try(Socket socket = new Socket("localhost", 6666)) {
			System.out.println("client 已连接");

			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void serverTimeOutTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); // 模拟阻塞，在客户端设置超时
			System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
			serverSocket.accept();
			System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void clientTimeOutTest() {
		try(Socket socket = new Socket()) {
			InetSocketAddress socketAddress = new InetSocketAddress("localhost", 6666);
			socket.connect(socketAddress, 1);
			System.out.println("client 已连接");

			//TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 四次挥手测试服务端
	@Test
	public void serverTest2() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
			serverSocket.accept();
			System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
			// TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 四次挥手测试客户端
	@Test
	public void clientTest2() {
		try(Socket socket = new Socket("localhost", 6666)) {
			System.out.println("client 已连接");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 单线程模拟持久运行的客户端，即可以接收多个客户端连接
	@Test
	public void singleThreadsAcceptMultiSocketServerTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			while (true) {
				System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
				Socket socket = serverSocket.accept();
				socket.setSoLinger(true, 100);
				socket.setSoTimeout(100);
				System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
				System.out.println("socket = " + socket.getPort());
				System.out.println("----------------------------------------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 多线程模拟持久运行的客户端，即可以接收多个客户端连接
	@Test
	public void multiThreadAcceptMultiSocketServerTest() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			while (true) {
				System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
				Socket socket = serverSocket.accept();
				System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
				System.out.println("socket = " + socket.getPort());
				System.out.println("----------------------------------------");

				new SocketHandler(socket).start();
				socket = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 单线程模拟持久运行的客户端，即可以接收多个客户端连接，这里采用timeout来控制，看不出来效果
	@Test
	public void singleThreadsAcceptMultiSocketServerTest2() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			while (true) {
				System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
				serverSocket.setSoTimeout(5000);
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(5000);
				System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
				System.out.println("socket = " + socket.getPort());
				System.out.println("----------------------------------------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 多线程模拟持久运行的客户端，即可以接收多个客户端连接，采用timeout机制
	@Test
	public void multiThreadAcceptMultiSocketServerTest2() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			while (true) {
				System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
				Socket socket = serverSocket.accept();
				// socket.setSoTimeout(5000);
				socket.setSoLinger(true, 5000);
				System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
				System.out.println("socket = " + socket.getPort());
				System.out.println("----------------------------------------");

				new SocketHandler(socket).start();
				socket = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 多线程模拟持久运行的客户端，即可以接收多个客户端连接
	@Test
	public void multiThreadAcceptMultiSocketServerTest4() {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			while (true) {
				System.out.println("server 阻塞开始 = " + System.currentTimeMillis());
				Socket socket = serverSocket.accept();
				System.out.println("server 阻塞结束 = " + System.currentTimeMillis());
				System.out.println("socket = " + socket.getPort());
				System.out.println("----------------------------------------");

				new SocketCloseHandler(socket).start();
				socket = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 通过调用socket的close方法会导致，服务端一直处于CLOSE_WAIT状态，让socket一直无法关闭
	@Test
	public void clientTest3() {
		try {
			Socket socket = new Socket("localhost", 6666);
			System.out.println("client 已连接");
			socket.shutdownOutput();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SocketCloseHandler extends Thread {
	private Socket socket;
	public SocketCloseHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(10);
			socket.close();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}

class SocketHandler extends Thread {
	private Socket socket;
	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while (!socket.isClosed()) {
			try {
				System.out.printf("Socket port = %s, isBound = %s, isInputShutdown = %s, isOutputShutdown = %s, isConnected = %s, isClosed = %s\r\n",
						socket.getPort(), socket.isBound(), socket.isInputShutdown(), socket.isOutputShutdown(), socket.isConnected(), socket.isClosed());
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (Objects.nonNull(socket)) {
			try {
				socket.close();
				System.out.println("socket close");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
