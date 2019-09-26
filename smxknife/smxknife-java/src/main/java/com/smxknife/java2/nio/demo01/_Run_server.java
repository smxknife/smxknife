package com.smxknife.java2.nio.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/10
 */
public class _Run_server {
	public static void main(String[] args) throws InterruptedException {
		Server server = new Server.Builder(12345).build();
		try {
			server.init();
		} catch (Exception e) {
			e.printStackTrace();
			server.stop();
		}

		TimeUnit.HOURS.sleep(1);
	}
}
