package com.smxknife.flink.demo.demo01;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/4/24
 */
public class TransactionSource implements SourceFunction<Transaction> {
	private volatile boolean stop = false;

	private Transaction[] transactions = new Transaction[] {
			new Transaction(1, 13.01),
			new Transaction(1, 25.0),
			new Transaction(1, 0.09),
			new Transaction(2, 1000.0),
			new Transaction(1, 510.0),
			new Transaction(1, 102.62),
			new Transaction(1, 91.50),
			new Transaction(1, 0.02),
			new Transaction(1, 30.01),
			new Transaction(1, 701.83),
			new Transaction(1, 31.92)
	};
	private AtomicInteger idx = new AtomicInteger(0);
	private volatile boolean delay = false;

	@Override
	public void run(SourceContext<Transaction> sourceContext) throws Exception {
		while (!stop) {
			System.out.println(Thread.currentThread().getName());
			int currentIndex = idx.getAndIncrement();
			if (currentIndex >= transactions.length) {
				synchronized (idx) {
					if (currentIndex >= transactions.length) {
						currentIndex = idx.updateAndGet(v -> 0);
						this.delay = true;
					}
				}
			}
			sourceContext.collectWithTimestamp(transactions[currentIndex],
					LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			if (delay) {
				TimeUnit.SECONDS.sleep(60);
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}

	@Override
	public void cancel() {
		this.stop = true;
	}
}
