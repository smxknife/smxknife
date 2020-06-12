package com.smxknife.zookeeper.simple;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/2
 */
public class _RunClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		Client client = new Client("localhost:2181");
		client.startZK();
		String name = client.queueCommand("cmd");
		System.out.println("created " + name);
		client.stopZK();
	}
}
