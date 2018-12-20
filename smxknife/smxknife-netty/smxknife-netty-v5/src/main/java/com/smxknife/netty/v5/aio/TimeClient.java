package com.smxknife.netty.v5.aio;

import java.io.IOException;

/**
 * @author smxknife
 * 2018-11-30
 */
public class TimeClient {
	public static void main(String[] args) throws IOException {
		int port = 8080;
		String ip = "127.0.0.1";

		new Thread(new AsyncTimeClientHandler(port, ip), "AIO-AsyncTimeClientHandler-001").start();
	}
}
