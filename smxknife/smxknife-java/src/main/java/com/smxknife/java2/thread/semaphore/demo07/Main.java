package com.smxknife.java2.thread.semaphore.demo07;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-22
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		RepastService service = new RepastService();
		ThreadP[] threadPs = new ThreadP[60];
		ThreadC[] threadCs = new ThreadC[60];

		for (int i = 0; i < 60; i++) {
			threadPs[i] = new ThreadP(service);
			threadCs[i] = new ThreadC(service);
		}

		Thread.sleep(2000);

		Stream.iterate(0, idx -> idx + 1).limit(60)
				.forEach(idx -> {
					threadPs[idx].start();
					threadCs[idx].start();
				});
	}
}
