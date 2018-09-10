package com.smxknife.shardingsphere.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppBoot {

	public static void main(String[] args) {
		DataSourceConfig config = new DataSourceConfig();
		DataSource dataSource = config.dataSource();
		String sql = "SELECT o.* FROM t_order o WHERE o.user_id=? AND o.order_id=?";

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 10);
			statement.setInt(2, 100);
			try (ResultSet rs = statement.executeQuery()){
				while (rs.next()) {
					System.out.println(rs.getInt(1));
					System.out.println(rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
