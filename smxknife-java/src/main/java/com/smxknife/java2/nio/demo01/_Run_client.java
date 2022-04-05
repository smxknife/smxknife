package com.smxknife.java2.nio.demo01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/5
 */
public class _Run_client {
	public static void main(String[] args) throws InterruptedException, IOException {
		Client client = new Client.Builder("localhost", 12345).build();

		try {
			client.init();
		} catch (IOException e) {
			e.printStackTrace();
			client.stop();
		}

		TimeUnit.SECONDS.sleep(10);
		client.stop();

		TimeUnit.SECONDS.sleep(5);
	}
}
