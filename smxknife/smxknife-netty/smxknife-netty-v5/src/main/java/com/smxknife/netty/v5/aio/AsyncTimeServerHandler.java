package com.smxknife.netty.v5.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2018-11-30
 */
public class AsyncTimeServerHandler implements Runnable {

	int port;

	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;

	public AsyncTimeServerHandler(int port) throws IOException {
		this.port = port;
		asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
		asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
		System.out.println("The timeServer is start in port : " + port);
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doAccept() {
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}
}
