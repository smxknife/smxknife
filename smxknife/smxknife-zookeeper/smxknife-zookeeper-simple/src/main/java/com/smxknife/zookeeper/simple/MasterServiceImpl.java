package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

/**
 * @author smxknife
 * 2019/12/1
 */
public class MasterServiceImpl implements MasterService {
	@Override
	public void runForMaster(Master master) throws InterruptedException {
		while (true) {
			try {
				master.getZk().create(master.getPath(), master.getServerId().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				master.setLeader(true);
				break;
			} catch (KeeperException e) {
				KeeperException.Code code = e.code();
				if (code.equals(KeeperException.Code.NODEEXISTS)) {
					master.setLeader(false);
					break;
				}
			}
			if (checkMaster(master)) {
				break;
			}
		}
	}

	@Override
	public boolean checkMaster(Master master) throws InterruptedException {
		while (true) {
			try {
				Stat stat = new Stat();
				byte[] data = master.getZk().getData(master.getPath(), false, stat);
				master.setLeader(new String(data).equals(master.getServerId()));
				return true;
			} catch (KeeperException e) {
				KeeperException.Code code = e.code();
				switch (code) {
					case CONNECTIONLOSS:
				}
				if (e.code().equals(KeeperException.Code.NONODE)) {
					return false;
				}
			}
		}
	}

	@Override
	public void bootstrap(Master master) {
		createParent(master, "/workers", new byte[0]);
		createParent(master, "/tasks", new byte[0]);
		createParent(master, "/assign", new byte[0]);
		createParent(master, "/status", new byte[0]);
	}

	private void createParent(Master master, String path, byte[] data) {
		try {
			master.getZk().create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
