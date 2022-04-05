package com.smxknife.druid.demo01;

import java.sql.*;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/18
 */
public class Main {
	public static void main(String[] args) {
		// Connect to /druid/v2/sql/avatica/ on your Broker.
		String url = "jdbc:avatica:remote:url=http://localhost:8082/druid/v2/sql/avatica/";

		// Set any connection context parameters you need here (see "Connection context" below).
		// Or leave empty for default behavior.
		Properties connectionProperties = new Properties();

		try (Connection connection = DriverManager.getConnection(url, connectionProperties)) {
			try (
					final PreparedStatement statement = connection.prepareStatement("select * from ent_Data");
					final ResultSet resultSet = statement.executeQuery()
			) {
				while (resultSet.next()) {
					System.out.println(String.format("__time = %s | count = %d | dataCode = %s | entCode = %s | statType = %s | value = %f",
							resultSet.getString(1),
							resultSet.getInt(2),
							resultSet.getString(3),
							resultSet.getString(4),
							resultSet.getString(5),
							resultSet.getDouble(6)));
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
