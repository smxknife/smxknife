package com.smxknife.java2.jdbc.demo01;

import java.sql.*;

/**
 * @author smxknife
 * 2020/11/9
 */
public class _Main {
	public static void main(String[] args) {
		// TODO：这个代码可能会报错，因为在当前环境中没有提供mysql的驱动包，所以会找不到驱动
		try {
			// 第一步：注册驱动
			// TODO: 这是在早期版本中需要这样写，后面的版本采用了SPI的方式，可以不需要反射
			// Class.forName("com.mysql.jdbc.Driver");

			// 第二步：获取connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

			// 第三步：创建statement
			Statement statement = connection.createStatement();

			// 第四部：执行sql，并获取返回结果ResultSet
			ResultSet resultSet = statement.executeQuery("select * from Blog");

			// 第五步：根据表结构从ResultSet获取每一列值
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String title = resultSet.getString("title");
				long author_id = resultSet.getLong("author_id");
				System.out.printf("Blog id = %s, title = %s, author_id = %s\r\n", id, title, author_id);
			}

			// 第六步：关闭资源
			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}