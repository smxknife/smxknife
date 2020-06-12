package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/2
 */
public class _RunAdminClient {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		AdminClient adminClient = new AdminClient("localhost:2181");
		adminClient.startZK();
		adminClient.listState();
		adminClient.stopZK();
	}
}
