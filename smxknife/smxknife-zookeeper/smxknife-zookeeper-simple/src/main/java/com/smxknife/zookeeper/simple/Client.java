package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

/**
 * @author smxknife
 * 2019/12/2
 */
public class Client extends Znode {

	public Client(String hostPort) {
		super(hostPort);
	}

	public String queueCommand(String command) throws InterruptedException {
		while (true) {
			String name = "";
			try {
				name = zk.create("/tasks/task-", command.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
				return name;
			} catch (KeeperException e) {
				switch (e.code()) {
					case NODEEXISTS:
						throw new RuntimeException(name + " already appears to be running");
				}
			}
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("client " + event);
	}
}
