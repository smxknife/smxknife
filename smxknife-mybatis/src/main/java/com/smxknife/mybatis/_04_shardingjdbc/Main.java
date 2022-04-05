package com.smxknife.mybatis._04_shardingjdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author smxknife
 * 2021/6/28
 */
public class Main {
	public static void main(String[] args) throws SQLException {
		// 配置真实数据源
		Map<String, DataSource> dataSourceMap = new HashMap<>();

		// 配置第一个数据源
		HikariDataSource dataSource1 = new HikariDataSource();
		dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/sharding_test_0");
		dataSource1.setUsername("root");
		dataSource1.setPassword("root");
		dataSourceMap.put("blog0", dataSource1);

		// 配置第二个数据源
		HikariDataSource dataSource2 = new HikariDataSource();
		dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/sharding_test_1");
		dataSource2.setUsername("root");
		dataSource2.setPassword("root");
		dataSourceMap.put("blog1", dataSource2);

		// 配置第三个数据源
		HikariDataSource dataSource3 = new HikariDataSource();
		dataSource3.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource3.setJdbcUrl("jdbc:mysql://localhost:3306/sharding_test_2");
		dataSource3.setUsername("root");
		dataSource3.setPassword("root");
		dataSourceMap.put("blog2", dataSource3);

		// 配置blog表规则
	    TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration("blog","blog${0..2}.blog");

		// 配置分库 + 分表策略
		tableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "blog${id % 3}"));

		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfig);

		// 获取数据源对象
		DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
	}
}
