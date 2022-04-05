package com.smxknife.java2.nio.demo01;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/11
 */
public class _Run_multi_client {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					Client client = new Client.Builder("localhost", 12345)
							.name("client_" + idx)
							.executorService(executorService)
							.build();
					try {
						client.init();
					} catch (IOException e) {
						e.printStackTrace();
						try {
							client.stop();
						} catch (IOException ex) {
							ex.addSuppressed(e);
							ex.printStackTrace();
						}
					}
				});

		TimeUnit.MINUTES.sleep(2);
	}
}
