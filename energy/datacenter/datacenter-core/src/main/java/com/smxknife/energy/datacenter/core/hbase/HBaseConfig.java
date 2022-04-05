package com.smxknife.energy.datacenter.core.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author smxknife
 * 2021/5/20
 */
@Configuration
public class HBaseConfig {

	@Value("${hbase.zookeeper.quorum:localhost}")
	private String zkHost;
	@Value("${hbase.zookeeper.property.clientPort:2181}")
	private String zkPort;

	@Bean
	public Connection connection() throws IOException {
		final org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", zkHost);
		conf.set("hbase.zookeeper.property.clientPort", zkPort);
		return ConnectionFactory.createConnection(conf);
	}


}
