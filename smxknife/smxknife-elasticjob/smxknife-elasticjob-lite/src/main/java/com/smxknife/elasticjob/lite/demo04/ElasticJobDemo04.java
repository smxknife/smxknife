package com.smxknife.elasticjob.lite.demo04;

import com.smxknife.elasticjob.lite.CommonTest;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;

/**
 * @author smxknife
 * 2020/9/21
 */
public class ElasticJobDemo04 {
	public static void main(String[] args) {
		new ScheduleJobBootstrap(CommonTest.createRegistryCenter("elastic_04"), new MockMisfireJob(), createJobConfiguration()).schedule();
	}

	public static JobConfiguration createJobConfiguration() {
		return JobConfiguration.newBuilder("MockMisfireJob", 2).cron("0/5 * * * * ?")
				.overwrite(true).monitorExecution(true).build();
	}
}
