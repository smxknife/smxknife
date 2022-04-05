package com.smxknife.elasticjob.lite.demo03;

import com.smxknife.elasticjob.lite.CommonTest;
import com.smxknife.elasticjob.lite.MyJob;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义追踪处理，这里写的日志方式，可以改为flink作为实时监控的原始数据源 => 到kafka -> flink
 * @author smxknife
 * 2020/9/19
 */
public class ElasticJobDemo03 {
	public static void main(String[] args) {
		new ScheduleJobBootstrap(CommonTest.createRegistryCenter("elastic_03"), new MyJob(),
				CommonTest.createJobConfiguration(), createLogTracingConfiguration(), CommonTest.loadJobListeners())
				.schedule();
	}

	private static TracingConfiguration createLogTracingConfiguration() {
		Logger jobLogger = LoggerFactory.getLogger(ElasticJobDemo03.class);
		return new TracingConfiguration<Logger>("LOG", jobLogger);
	}
}
