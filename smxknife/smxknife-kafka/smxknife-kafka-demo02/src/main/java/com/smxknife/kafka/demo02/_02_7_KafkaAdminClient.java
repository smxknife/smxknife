package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author smxknife
 * 2020/9/7
 */
public class _02_7_KafkaAdminClient {
	static String brokerList = "localhost:9092";
	static String topic = "test-admin";
	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

		AdminClient client = AdminClient.create(properties);

		// createTopic(client);

		// listTopic(client);

		// describeTopic(client);

//		System.out.println("before alter");
//		describeConfigs(client);
//		alterConfig(client);
//		System.out.println("after alter");
//		describeConfigs(client);

		System.out.println("before createPartitons");
		describeTopic(client);
		createPatritions(client);
		System.out.println("after createPartitons");
		describeTopic(client);

		client.close();
	}

	private static void createPatritions(AdminClient client) {
		Map<String, NewPartitions> map = new HashMap<>();
		NewPartitions partitions = NewPartitions.increaseTo(4);
		map.put(topic, partitions);
		CreatePartitionsResult partitions1 = client.createPartitions(map);
		try {
			partitions1.all().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void describeConfigs(AdminClient client) {
		ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
		DescribeConfigsResult describeConfigsResult = client.describeConfigs(Collections.singletonList(configResource));
		try {
			Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();
			System.out.println(configResourceConfigMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void alterConfig(AdminClient client) {
		ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
		ConfigEntry configEntry = new ConfigEntry("cleanup.policy", "delete");
		Config config = new Config(Collections.singletonList(configEntry));
		Map<ConfigResource, Config> configs = new HashMap<>();
		configs.put(configResource, config);
		AlterConfigsResult alterConfigsResult = client.alterConfigs(configs);
		try {
			alterConfigsResult.all().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void describeTopic(AdminClient client) {
		DescribeTopicsResult describeTopicsResult = client.describeTopics(Collections.singletonList(topic));
		try {
			Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
			System.out.println(stringTopicDescriptionMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void listTopic(AdminClient client) {
		ListTopicsResult listTopicsResult = client.listTopics();
		try {
			listTopicsResult.listings().get().forEach(t -> {
				System.out.println(t.name() + " | isInternal : " + t.isInternal());
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("-------");
		ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
		listTopicsOptions.listInternal(true);
		ListTopicsResult listTopicsResult1 = client.listTopics(listTopicsOptions);

		try {
			listTopicsResult1.listings().get().forEach(t -> {
				System.out.println(t.name() + " | isInternal : " + t.isInternal());
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void createTopic(AdminClient client) {
		NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
		CreateTopicsResult topicsResult = client.createTopics(Collections.singletonList(newTopic));

		try {
			topicsResult.all().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
