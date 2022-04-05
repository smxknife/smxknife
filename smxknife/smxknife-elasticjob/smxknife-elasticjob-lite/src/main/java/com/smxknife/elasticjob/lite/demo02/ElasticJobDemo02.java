package com.smxknife.elasticjob.lite.demo02;

import com.smxknife.elasticjob.lite.MyJob;
import com.zaxxer.hikari.util.DriverDataSource;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;

import java.util.Properties;

import static com.smxknife.elasticjob.lite.CommonTest.*;

/**
 * @author smxknife
 * 2020/9/18
 */
public class ElasticJobDemo02 {
	public static void main(String[] args) {
		new ScheduleJobBootstrap(createRegistryCenter("elastic_02"), new MyJob(), createJobConfiguration(), createTracingConfig(), loadJobListeners()).schedule();
	}

	private static TracingConfiguration createTracingConfig() {
		DriverDataSource dataSource = new DriverDataSource("jdbc:mysql://localhost:3306/elasticjob?characterEncoding=utf-8&autoReconnect=true&useSSL=false", "com.mysql.cj.jdbc.Driver", new Properties(), "root", "root");
		return new TracingConfiguration("RDB", dataSource);
	}


}
