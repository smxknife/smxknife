package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

/**
 * @author smxknife
 * 2019/12/1
 */
public class MasterAsyncServiceImpl implements MasterService {

	@Override
	public void runForMaster(Master master) throws InterruptedException {
		master.getZk().create(master.getPath(), master.getServerId().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback(master), null);
	}

	@Override
	public boolean checkMaster(Master master) throws InterruptedException {
		master.getZk().getData(master.getPath(), false, masterCheckCallback(master), null);
		return false;
	}

	@Override
	public void bootstrap(Master master) {
		System.out.println("master bootstrap");
		createParent(master, "/workers", new byte[0]);
		createParent(master, "/tasks", new byte[0]);
		createParent(master, "/assign", new byte[0]);
		createParent(master, "/status", new byte[0]);
	}

	private void createParent(Master master, String path, byte[] data) {
		master.getZk().create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, String name) {
				switch (KeeperException.Code.get(rc)) {
					case CONNECTIONLOSS:
						// 这里的data等价于(byte[]) ctx
						createParent(master, path, data);
						break;
					case OK:
						System.out.println("Parent [" + path + "] created");
						break;
					default:
						System.err.println("Something went wrong: " + KeeperException.create(KeeperException.Code.get(rc), path));
				}
			}
		}, data); // 这里的data，需要稍微解释一下，因为我们不需要给节点内容，所以需要传空字符串，而这里的data其实就是空字符串
	}

	private AsyncCallback.StringCallback masterCreateCallback(Master master) {
		return new AsyncCallback.StringCallback() {

			/**
			 *
			 * @param rc 返回的调用结构，返回OK或KeeperException对应的编码
			 * @param path create的参数值
			 * @param ctx 传递给create的上下文参数
			 * @param name znode节点名称，如果是非序列化的，path和name一样，如果是序列化的，这两个会不一样
			 */
			@Override
			public void processResult(int rc, String path, Object ctx, String name) {
				switch (KeeperException.Code.get(rc)) {
					case CONNECTIONLOSS:
						try {
							System.out.println("masterCreateCallback CONNECTIONLOSS");
							checkMaster(master);
						} catch (InterruptedException e) {
						}
						return;
					case OK:
						System.out.println("masterCreateCallback OK");
						master.setLeader(true);
						break;
					default:
						System.out.println("masterCreateCallback " + KeeperException.Code.get(rc));
						master.setLeader(false);
				}
				System.out.println("I'm " + (master.isLeader() ? "" : "not ") + "the leader");
			}
		};
	}

	private AsyncCallback.DataCallback masterCheckCallback(Master master) throws InterruptedException {
		return new AsyncCallback.DataCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
				switch (KeeperException.Code.get(rc)) {
					case CONNECTIONLOSS:
						try {
							checkMaster(master);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return;
					case NONODE:
						try {
							runForMaster(master);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					default:
						master.setLeader(false);
				}
			}
		};
	}
}
