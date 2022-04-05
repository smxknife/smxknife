package com.smxknife.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.api.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.api.listener.ShardingContexts;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @author smxknife
 * 2020/9/19
 */
public class CommonTest {
	public static JobConfiguration createJobConfiguration() {
		return JobConfiguration.newBuilder("MyJob", 2).cron("0/5 * * * * ?").build();
	}

	public static CoordinatorRegistryCenter createRegistryCenter(String namespace) {
		ZookeeperRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2191", namespace));
		registryCenter.init();
		return registryCenter;
	}

	public static ElasticJobListener loadJobListeners() {
		return new ElasticJobListener() {

			@Override
			public void beforeJobExecuted(ShardingContexts shardingContexts) {
				System.out.println("beforeJobExecuted...");
			}

			@Override
			public void afterJobExecuted(ShardingContexts shardingContexts) {
				System.out.println("afterJobExecuted...");
			}
		};
	}
}
