package com.smxknife.java2.nio.demo01;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/9/10
 */
public class Client implements Nio {

	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private boolean configureBlocking = false;
	private long selectTimeout = Constant.SELECT_TIMEOUT;
	private SelectionKeyHandler selectionKeyHandler;

	private volatile boolean stop = false;
	private ExecutorService executor;
	private SocketAddress socketAddress;
	private String name = Constant.CLIENT_DEFAULT_NAME;

	public void init() throws IOException {
		this.socketAddress = new InetSocketAddress(host, port);
		this.selector = Selector.open();
		this.socketChannel = SocketChannel.open();
		this.socketChannel.configureBlocking(this.configureBlocking);
		if (Objects.isNull(this.selectionKeyHandler)) {
			this.selectionKeyHandler = new ClientSelectionKeyHandler() {};
		}
		doConnect();
		if (Objects.isNull(executor)) {
			executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
					.setNameFormat("client-task-pool-thread-" + this.name + "-%d")
					.setDaemon(true)
					.build());
		}
		executor.execute(new SelectorTask(this));
	}

	public void stop() throws IOException {
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
		return this.selectionKeyHandler;
	}

	@Override
	public SocketAddress getSocketAddress() {
		return this.socketAddress;
	}

	@Override
	public String getName() {
		return this.name;
	}

	private void doConnect() throws IOException {
		boolean isConnected = this.selectionKeyHandler.doConnect(this.socketChannel, this.selector, this.socketAddress);
		if (isConnected) {
			this.selectionKeyHandler.doWrite(this.socketChannel, String.format(Constant.CLIENT_CONNECT_TO_SERVER_MSG_TEMPLATE, this.name, LocalDateTime.now().toString()));
		}
	}

	private Client(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.configureBlocking = builder.configureBlocking;
		this.selectTimeout = builder.selectTimeout;
		this.selectionKeyHandler = builder.selectionKeyHandler;
		this.executor = builder.executor;
		this.name = builder.name;
	}

	public static class Builder {
		private String host;
		private int port;
		private boolean configureBlocking = false;
		private long selectTimeout = Constant.SELECT_TIMEOUT;
		private SelectionKeyHandler selectionKeyHandler;
		private ExecutorService executor;
		private String name = Constant.CLIENT_DEFAULT_NAME;

		public Builder(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public Builder name(String name) {
			this.name = name;
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

		public Builder selectionKeyHandler(SelectionKeyHandler selectionKeyHandler) {
			this.selectionKeyHandler = selectionKeyHandler;
			return this;
		}

		public Builder executorService(ExecutorService executorService) {
			this.executor = executorService;
			return this;
		}

		public Client build() {
			return new Client(this);
		}
	}
}
