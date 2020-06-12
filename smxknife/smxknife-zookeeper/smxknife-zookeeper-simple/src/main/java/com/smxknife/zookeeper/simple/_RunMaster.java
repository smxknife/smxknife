package com.smxknife.zookeeper.simple;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/11/30
 */
public class _RunMaster {
	public static void main(String[] args) throws IOException, InterruptedException {
		MasterService masterService = new MasterAsyncServiceImpl();
//		MasterService masterService = new MasterServiceImpl();
		Master master = new Master("localhost:2181", "/master", masterService);
		master.startZK();
		master.runForMaster();
		int num = 0;
		while (num < 15) {
			if (master.isLeader()) {
				System.out.println("I'm the leader");
				masterService.bootstrap(master);
				num = 15; // 退出循环
			} else {
				num++;
				System.out.println("Someone else is leader");
				TimeUnit.SECONDS.sleep(1);
			}
		}


		TimeUnit.MINUTES.sleep(10);
		master.stopZK();
	}
}
