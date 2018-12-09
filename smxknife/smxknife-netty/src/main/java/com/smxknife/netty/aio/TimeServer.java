package com.smxknife.netty.aio;

import java.io.IOException;

/**
 * @author smxknife
 * 2018-11-30
 */
public class TimeServer {
	public static void main(String[] args) throws IOException {
		int port = 8080;

		new Thread(new AsyncTimeServerHandler(port), "AIO-AsyncTimeServerHandler-001").start();
	}
}
