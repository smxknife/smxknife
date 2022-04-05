package com.smxknife.hbase.demo03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author smxknife
 * 2021/5/23
 */
public class _Test03_Phoenix {


	Connection conn = null;

	/**
	 * rowkey = 时间 + energyCode + 企业代码
	 * 列族：energy
	 * - energyCode
	 * - regionCode
	 * - industryCode
	 * - domainCode
	 * - dataCode
	 * - dataValue
	 * - dataTime
	 */
	@Test
	public void select() throws SQLException {
		String sql = "select \"regionCode\", sum(\"dataValue\") as dataValue from \"ent_energy_dws\" group by \"energy\".\"regionCode\"";
		final Statement statement = conn.createStatement();
		final ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			//final String pk = resultSet.getString("pk");
//			final String energyCode = resultSet.getString("energyCode");
			final String regionCode = resultSet.getString("regionCode");
//			final String industryCode = resultSet.getString("industryCode");
//			final String dataCode = resultSet.getString("dataCode");
			final long dataValue = resultSet.getLong("dataValue");
//			final long dataTime = resultSet.getLong("dataTime");
//			System.out.println(energyCode + ", " + regionCode + ", " + industryCode + ", " + dataCode + ", " + dataValue + ", " + dataTime);
			System.out.println(regionCode + ": " + dataValue);
		}
	}

	@Test
	public void createTable() {
		// 注意，如果想要从phoenix查数据，需要建表做映射，如果是仅仅查询可以做视图
		/*
		create view "ent_energy_dws( 
			pk VARCHAR PRIMARY KEY,
			"energy"."energyCode" VARCHAR,
			"energy"."regionCode" VARCHAR,
			"energy"."industryCode" VARCHAR,
			"energy"."dataCode" VARCHAR,
			"energy"."dataValue" INTEGER,
			"energy"."dataTime" BIGINT);

		 */
	}

	@Before
	public void init() throws SQLException {
		String url = "jdbc:phoenix:localhost:2181";
		conn = DriverManager.getConnection(url);

	}

	@After
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
