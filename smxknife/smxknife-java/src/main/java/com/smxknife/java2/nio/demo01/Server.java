package com.smxknife.java2.nio.demo01;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/9/10
 */
public class Server implements Nio {

	private int port;
	private int backlog = 1024;
	private boolean configureBlocking = false;
	private long selectTimeout = Constant.SELECT_TIMEOUT;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private SelectionKeyHandler selectionKeyHandler;

	private ExecutorService executor;
	private volatile boolean stop = false;
	private SocketAddress socketAddress;


	public void init() throws IOException {
		this.socketAddress = new InetSocketAddress(port);
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(this.socketAddress, backlog);
		serverSocketChannel.configureBlocking(configureBlocking);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("server is start in port : " + this.port);
		if (Objects.isNull(selectionKeyHandler)) {
			selectionKeyHandler = new ServerSelectionKeyHandler() {};
		}
		if (Objects.isNull(executor)) {
			executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
					.setNameFormat("server-select-task-pool-thread-%d")
					.setDaemon(true)
					.build());
		}
		executor.execute(new SelectorTask(this));
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public boolean isStop() {
		return this.stop;
	}

	@Override
	public Selector getSelector() {
		return this.selector;
	}

	@Override
	public long getSelectTimeout() {
		return this.selectTimeout;
	}

	@Override
	public SelectionKeyHandler getSelectionKeyHandler() {
		return selectionKeyHandler;
	}

	@Override
	public SocketAddress getSocketAddress() {
		return this.socketAddress;
	}

	private Server(Builder builder) {
		this.port = builder.port;
		this.backlog = builder.backlog;
		this.configureBlocking = builder.configureBlocking;
		this.selectTimeout = builder.selectTimeout;
		this.executor = builder.executor;
		this.selectionKeyHandler = builder.selectionKeyHandler;
	}

	public static class Builder {

		private int port;
		private int backlog = 1024;
		private boolean configureBlocking = false;
		private long selectTimeout = Constant.SELECT_TIMEOUT;
		private ExecutorService executor;
		private SelectionKeyHandler selectionKeyHandler;

		public Builder(int port) {
			this.port = port;
		}

		public Builder backlog(int backlog) {
			this.backlog = backlog;
			return this;
		}

		public Builder configureBlocking(boolean configureBlocking) {
			this.configureBlocking = configureBlocking;
			return this;
		}

		public Builder selectTimeout(long selectTimeout) {
			this.selectTimeout = selectTimeout;
			return this;
		}

		public Builder executorService(ExecutorService executorService) {
			this.executor = executorService;
			return this;
		}

		public Builder selectionKeyHandler(SelectionKeyHandler selectionKeyHandler) {
			this.selectionKeyHandler = selectionKeyHandler;
			return this;
		}

		public Server build() {
			return new Server(this);
		}
	}

}
