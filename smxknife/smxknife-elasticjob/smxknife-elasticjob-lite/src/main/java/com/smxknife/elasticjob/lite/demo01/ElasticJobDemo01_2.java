package com.smxknife.elasticjob.lite.demo01;

import com.smxknife.elasticjob.lite.MyJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @author smxknife
 * 2020/9/18
 */
public class ElasticJobDemo01_2 {
	public static void main(String[] args) {
		new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();
	}

	private static JobConfiguration createJobConfiguration() {
		return JobConfiguration.newBuilder("MyJob", 3).cron("0/5 * * * * ?").overwrite(true).monitorExecution(true).build();
	}

	private static CoordinatorRegistryCenter createRegistryCenter() {
		CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2191", "elastic_01"));
		registryCenter.init();
		return registryCenter;
	}


}
