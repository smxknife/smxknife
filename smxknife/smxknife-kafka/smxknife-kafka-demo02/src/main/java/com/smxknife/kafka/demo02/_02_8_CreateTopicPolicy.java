package com.smxknife.kafka.demo02;

import org.apache.kafka.common.errors.PolicyViolationException;
import org.apache.kafka.server.policy.CreateTopicPolicy;

import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/9/7
 */
public class _02_8_CreateTopicPolicy implements CreateTopicPolicy {
	public static void main(String[] args) {

	}

	@Override
	public void validate(RequestMetadata requestMetadata) throws PolicyViolationException {
		// 在这里进行验证
		Map<String, String> configs = requestMetadata.configs();
		Integer partitions = requestMetadata.numPartitions();
		Map<Integer, List<Integer>> replicasAssignments = requestMetadata.replicasAssignments();
		Short replicationFactor = requestMetadata.replicationFactor();
		String topic = requestMetadata.topic();
		// 通过上面的参数就可以进行验证
	}

	@Override
	public void close() throws Exception {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
