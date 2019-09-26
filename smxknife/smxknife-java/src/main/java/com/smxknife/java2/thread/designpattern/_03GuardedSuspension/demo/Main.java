package com.smxknife.java2.thread.designpattern._03GuardedSuspension.demo;

/**
 * @author smxknife
 * 2019/9/24
 */
public class Main {
	public static void main(String[] args) {
		RequestQueue requestQueue = new RequestQueue();

		new ClientThread(requestQueue, "Client", 3141592L).start();
		new ServerThread(requestQueue, "Server", 6535897L).start();
	}
}
