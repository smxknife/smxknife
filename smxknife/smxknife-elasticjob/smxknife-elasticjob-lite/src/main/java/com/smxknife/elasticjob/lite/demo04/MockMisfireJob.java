package com.smxknife.elasticjob.lite.demo04;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/9/21
 */
public class MockMisfireJob implements SimpleJob {
	@Override
	public void execute(ShardingContext shardingContext) {
		int shardingItem = shardingContext.getShardingItem();
		switch (shardingItem) {
			case 1:
				try {
					System.out.println("ShardingItem = " + shardingItem + "| Mock busy logic...");
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("ShardingItem = " + shardingItem + "| Normal logic");
		}
	}
}
