package com.smxknife.zookeeper.simple;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.*;

/**
 * @author smxknife
 * 2019/12/1
 */
public class Worker extends Znode {

	@Getter
	@Setter
	private String status;

	public Worker(String hostPort, String path) {
		super(hostPort);
		this.path = path;
	}

	void register() {
		zk.create(path + serverId, "Idle".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, String name) {
				switch (KeeperException.Code.get(rc)) {
					case CONNECTIONLOSS:
						register();
						break;
					case OK:
						System.out.println(serverId + " registered successfully");
						break;
					case NODEEXISTS:
						System.err.println(serverId + " already registered");
						break;
					default:
						System.err.println(serverId + " something went wrong: " + KeeperException.create(KeeperException.Code.get(rc), path));
				}
			}
		}, null);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("worker [ " + this.serverId + "] " + hostPort + " : " + event);
	}
}
