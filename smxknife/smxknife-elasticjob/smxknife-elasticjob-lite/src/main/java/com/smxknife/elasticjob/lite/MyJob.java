package com.smxknife.elasticjob.lite;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author smxknife
 * 2020/9/18
 */
public class MyJob implements SimpleJob {
	@Override
	public void execute(ShardingContext shardingContext) {
		String jobName = shardingContext.getJobName();
		String jobParameter = shardingContext.getJobParameter();
		int shardingItem = shardingContext.getShardingItem();
		int shardingTotalCount = shardingContext.getShardingTotalCount();
		String taskId = shardingContext.getTaskId();
		String shardingParameter = shardingContext.getShardingParameter();

		System.out.println(String.format("jboName %s, shardingParameter %s, jobParameter %s, shardingItem %s, shardingTotalCount %s, taskId %s",
				jobName, shardingParameter, jobParameter, shardingItem, shardingTotalCount, taskId));

		switch (shardingItem) {
			case 0:
				System.out.println("shardingItem 0 handler...");
				break;
			case 1:
				System.out.println("shardingItem 1 handler...");
				break;
			case 2:
				System.out.println("shardingItem 2 handler...");
				break;
			default:
				System.out.println("shardingItem def handler...");
				break;
		}
	}
}
