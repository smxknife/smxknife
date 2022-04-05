package com.smxknife.lock.mysql;

import com.smxknife.lock.BaseLock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * 核心思想就是通过插入记录进行获取锁
 * create table d_lock (
 *      id int auto_increment,
 *      name varchar(100) not null,
 *      thread_id varchar(100) not null,
 *      expire_time bigint not null,
 *      primary key(id),
 *      unique(name)
 * ) engine=myisam
 *
 * @author smxknife
 * 2021/6/11
 */
public class MySqlMyIsamLock extends BaseLock {

	private static final String INSERT_SQL = "insert into d_lock(name,thread_id, expire_time) values(?, ?, ?)";
	private static final String UPDATE_SQL = "update d_lock set expire_time = ? where name = ?";
	private static final String DELETE_SQL = "delete from d_lock where name = ?";
	private static final String SELECT_SQL = "select * from d_lock where name = ?";


	private DataSource dataSource;
	private String lockName;
	private long expireMills;
	private long renewalMills;
	private String threadId;

	public MySqlMyIsamLock(String lockName, DataSource dataSource, long expireMills) {
		this.lockName = lockName;
		this.dataSource = dataSource;
		this.expireMills = expireMills;
		this.renewalMills = expireMills / 2;
		this.threadId = Thread.currentThread().getName() + UUID.randomUUID();
	}

	public void lock() {
		while (true) {
			try(Connection connection = dataSource.getConnection();
			    PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
			) {
				statement.setString(1, lockName);
				statement.setString(2, threadId);
				statement.setLong(3, System.currentTimeMillis() + expireMills);

				try {
					final int rows = statement.executeUpdate();
					if (rows > 0) {
						// 获取锁成功
						return;
					}
				} catch (Exception e) {

				}


			} catch (SQLException throwables) {
				// 获取锁失败
				throwables.printStackTrace();
			}
		}
	}
}
