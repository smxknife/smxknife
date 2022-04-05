package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/8/29
 */
public class _01_3_DemoPartitioner implements Partitioner {

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes1, Cluster cluster) {

		System.out.println("Partitioner partition...");

		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		int numPartitions = partitions.size();
		if (null == keyBytes) {
			return counter.getAndIncrement() % numPartitions;
		} else {
			return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
		}
	}

	@Override
	public void close() {
		System.out.println("Partitioner close...");
	}

	@Override
	public void configure(Map<String, ?> map) {
		System.out.println("Partitioner configure...");
	}
}
