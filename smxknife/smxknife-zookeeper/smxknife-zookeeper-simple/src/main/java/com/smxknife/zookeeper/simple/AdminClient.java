package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

/**
 * @author smxknife
 * 2019/12/2
 */
public class AdminClient extends Znode {

	public AdminClient(String hostPort) {
		super(hostPort);
	}

	void listState() throws InterruptedException, KeeperException {
		try {
			Stat stat = new Stat();
			byte[] masterData = zk.getData("/master", false, stat);
			Date startDate = new Date(stat.getCtime());
			System.out.println("Master: " + new String(masterData) + " since " + startDate);
		} catch (KeeperException e) {
			switch (e.code()) {
				case NODEEXISTS:
					System.out.println("No master");
					break;
				default:
					System.out.println(e.getMessage());
			}
		}
		System.out.println("Workers:");
		for (String child : zk.getChildren("/workers", false)) {
			byte[] data = zk.getData("/workers/" + child, false, null);
			String state = new String(data);
			System.out.println("\t" + child + " : " + state);
		};
		System.out.println("Tasks:");
		for (String child : zk.getChildren("/tasks", false)) {
			byte[] data = zk.getData("/tasks/" + child, false, null);
			String state = new String(data);
			System.out.println("\t" + child + " ： " + state);
		}
		System.out.println("Assign:");
		for (String ass : zk.getChildren("/assign", false)) {
			System.out.println("\t" + ass);
		}
	}



	@Override
	public void process(WatchedEvent event) {
		System.out.println("AdminClient: " + event);
	}
}
