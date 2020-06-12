package com.smxknife.zookeeper.simple;

import lombok.Getter;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author smxknife
 * 2019/12/1
 */
public abstract class Znode implements Watcher {
	@Getter
	protected ZooKeeper zk;
	protected String hostPort;
	@Getter
	protected String serverId;
	@Getter
	protected String path;

	protected Znode(String hostPort) {
		this.hostPort = hostPort;
		serverId = Integer.toHexString(ThreadLocalRandom.current().nextInt());
	}

	public void startZK() throws IOException {
		startZK(15000);
		// zk = new ZooKeeper(hostPort, 15000, this);
	}

	public void startZK(int timeout) throws IOException {
		zk = new ZooKeeper(hostPort, timeout, this);
	}

	public void stopZK() throws InterruptedException {
		Objects.requireNonNull(zk);
		// 虽然在客户端关闭后，服务端也会关闭会话，但是需要有一定的延迟，这样会造成资源浪费，高峰时期，会造成一定的风险，
		// 所以最好在客户端主动调用ZooKeeper.close方法来显示关闭，这样就会立刻销毁会话
		zk.close();
	}
}
