package com.smxknife.energy.services.metric.infras.consumer;

import com.smxknife.energy.services.metric.core.dao.DatapointDao;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author smxknife
 * 2021/5/17
 */
@Slf4j
public class TopicPartitionConsumerThread extends Thread {

	private BlockingQueue<ConsumerRecord<String, Datapoint>> blockingQueue = new LinkedBlockingQueue<>();

	private ConsumerCallback callback;
	private DatapointDao datapointDao;

	public TopicPartitionConsumerThread(String topic, int partition, DatapointDao datapointDao, ConsumerCallback callback) {
		this.callback = callback;
		this.datapointDao = datapointDao;
		this.setName(String.format("consumer-%s-%s", topic, partition));
	}

	public void appendRecord(ConsumerRecord<String, Datapoint> record) {
		blockingQueue.add(record);
	}

	public void appendRecords(List<ConsumerRecord<String, Datapoint>> records) {
		blockingQueue.addAll(records);
	}

	@Override
	public void run() {
		ConsumerRecord<String, Datapoint> record = null;
		try {
			while ((record = blockingQueue.take()) != null) {
				try {
					consume(record);
					callback.onSuccess(record.topic(), record.partition(), record.offset());
				} catch (Exception e) {
					// 这里可以加重试，如果不加重试会丢失数据，可以失败几次后丢弃，或者采用策略模式，做不同的处理
					log.error(e.getMessage(), e);
					boolean isSuccess = false;
					for (int i = 0; !isSuccess && i < 3; i++) {
						isSuccess = consume(record);
					}
					if (isSuccess) {
						callback.onSuccess(record.topic(), record.partition(), record.offset());
					} else {
						callback.onFail(record.topic(), record.partition(), record.offset());
					}
				}

			}
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}

	}

	private boolean consume(ConsumerRecord<String, Datapoint> record) throws InterruptedException {
		System.out.println("获取记录 " + record);
		final Datapoint datapoint = record.value();

		// 这里如果是mysql实现，需要在实现侧加回滚策略
		datapointDao.write(datapoint);
		return true;
	}
}
