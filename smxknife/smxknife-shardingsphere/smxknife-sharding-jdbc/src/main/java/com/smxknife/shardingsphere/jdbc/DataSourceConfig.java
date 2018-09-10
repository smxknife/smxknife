package com.smxknife.shardingsphere.jdbc;

import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.InlineShardingStrategyConfiguration;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceConfig {

	private DataSource dataSource;

	public DataSource dataSource() {
		return this.dataSource;
	}

	public DataSourceConfig() {
		try {
			this.config();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void config() throws SQLException {
		Map<String, DataSource> dataSourceMap = new HashMap<>();
		System.out.println("begin config");
		String userName = "root";
		String password = "root";
		String dsName0 = "ds0";
		dataSourceMap.put(dsName0, this.initDataSource(dsName0, userName, password));

		String dsName1 = "ds1";
		dataSourceMap.put(dsName1, this.initDataSource(dsName1, userName, password));

		// 配置order表规则
		TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
		tableRuleConfig.setLogicTable("t_order");
		tableRuleConfig.setActualDataNodes("ds${0..1}.t_order${0..1}");
		tableRuleConfig.setKeyGeneratorColumnName("order_id");

		// 配置分库 + 分表策略
		tableRuleConfig
				.setDatabaseShardingStrategyConfig(
						new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
		tableRuleConfig
				.setTableShardingStrategyConfig(
						new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfig);
		Properties prop = new Properties();
		prop.setProperty("sql.show", "true");
		dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), prop);
	}

	private DataSource initDataSource(String dataSourceName, String userName, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/" + dataSourceName);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}
}
