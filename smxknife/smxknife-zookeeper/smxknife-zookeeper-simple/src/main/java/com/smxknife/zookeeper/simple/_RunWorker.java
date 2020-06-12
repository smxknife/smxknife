package com.smxknife.zookeeper.simple;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/12/1
 */
public class _RunWorker {
	public static void main(String[] args) throws IOException, InterruptedException {
		Worker worker = new Worker("localhost:2181", "/workers/worker-");
		worker.startZK();
		worker.register();
		TimeUnit.MINUTES.sleep(10);
		worker.stopZK();
	}
}
