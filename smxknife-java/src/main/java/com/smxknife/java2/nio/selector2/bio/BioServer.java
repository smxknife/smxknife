package com.smxknife.java2.nio.selector2.bio;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/4/22
 */
public class BioServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
			boolean isStopped = false;

			while (!isStopped) {
				Socket socket = serverSocket.accept();
				new Thread() {
					Socket socket;

					Thread setSocket(Socket socket) {
						this.socket = socket;
						return this;
					}

					@SneakyThrows
					@Override
					public void run() {
						int port = this.socket.getPort();
						try (BufferedReader bf = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
							String data = bf.readLine();
							System.out.println(String.format("port: %s recv: %s", port, data));
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							this.socket.close();
						}
					}
				}.setSocket(socket).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(serverSocket) && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		}
	}
}
