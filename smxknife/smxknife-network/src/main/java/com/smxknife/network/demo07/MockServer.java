package com.smxknife.network.demo07;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/18
 */
public class MockServer {
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(6666)) {
			Socket socket = serverSocket.accept();
			TimeUnit.HOURS.sleep(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
