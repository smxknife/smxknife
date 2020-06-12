package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2019/11/26
 */
public class ZookeeperProSync implements Watcher {

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static Stat stat = new Stat();

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		//zookeeper配置数据存放路径
		String path = "/username";
		//连接zookeeper并且注册一个默认的监听器
		zk = new ZooKeeper("localhost:2181", 5000,
				new ZookeeperProSync());
		//等待zk连接成功的通知
		connectedSemaphore.await();
		//获取path目录节点的配置数据，并注册默认的监听器
		System.out.println(new String(zk.getData(path, true, stat)));

		Thread.sleep(Integer.MAX_VALUE);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("process event = " + event.toString());
		// zk连接成功通知事件
		if (Event.KeeperState.SyncConnected == event.getState()) {
			if (Event.EventType.None == event.getType() && null == event.getPath()) {
				System.out.println("EventType is None");
				connectedSemaphore.countDown();
			} else if (Event.EventType.NodeDataChanged == event.getType()) { //zk目录节点数据变化通知事件
				try {
					System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("连接不成功：" + event.getState());
		}
	}
}
