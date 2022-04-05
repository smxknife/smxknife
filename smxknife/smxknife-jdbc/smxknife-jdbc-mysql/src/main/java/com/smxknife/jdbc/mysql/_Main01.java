package com.smxknife.jdbc.mysql;

import java.sql.*;

/**
 * @author smxknife
 * 2021/5/8
 */
public class _Main01 {
	public static void main(String[] args) {
		try {
			// 1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 建立连接
			final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "root");
			// 2.1 测试连接
			System.out.println(connection);
			// 3. sql语句
			String sql = "select * from departments";
			// 4. 准备静态处理块对象，将sql放到静态语句块
			final Statement statement = connection.createStatement();
			// 5. 执行sql语句，返回结果集
			final ResultSet resultSet = statement.executeQuery(sql);
			// 6. 遍历结果集

			while (resultSet.next()) {
				// 需要特别注意，resultSet的column index 是从1开始的
				final String col1 = resultSet.getString(1);
				System.out.print(col1);
				System.out.print(" ");
				final String col2 = resultSet.getString(2);
				System.out.println(col2);
			}
			// 关闭资源
			statement.close();
			connection.close();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
