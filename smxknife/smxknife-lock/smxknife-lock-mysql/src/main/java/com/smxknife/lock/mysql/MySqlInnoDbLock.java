package com.smxknife.lock.mysql;

import com.smxknife.lock.BaseLock;
import lombok.NonNull;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 核心思路就是，创建一张表，对表中的锁对应的锁名的记录进行加锁（for update），
 * （innodb 由于gap和意向锁会造成死锁，rollback 性能下降，且机器down掉 造成死锁）
 * 有以下问题需要注意：
 * - create table d_lock (
 *      id int auto_increment,
 *      name varchar(100) not null,
 *      primary key(id),
 *      unique(name) -- 这里name必须加唯一索引
 * ) engine=InnoDB;
 *
 * @author smxknife
 * 2021/6/11
 */
public class MySqlInnoDbLock extends BaseLock {

	private static final String SELECT_SQL = "select * from d_lock l where l.name = ? for update";
	private static final String INSERT_SQL = "insert into d_lock(name) values(?)";

	private ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();

	private DataSource dataSource;
	private Connection connection;
	private String lockName;

	public MySqlInnoDbLock(@NonNull String lockName, @NonNull DataSource dataSource) {
		this.lockName = lockName;
		this.dataSource = dataSource;
	}

	public void lock() {

		PreparedStatement preparedStatement = null;
		try {

			while (true) {

				connection = dataSource.getConnection();
				// 这句是关键，不能自动提交，需要手动提交
				connection.setAutoCommit(false);
				preparedStatement = connection.prepareStatement(SELECT_SQL);
				preparedStatement.setString(1, this.lockName);
				// 执行这一查询的时候，如果是innodb，那么可以通过for update对查询的锁记录加上行锁，所以，如果锁已经被占用，那么这里会等待
				final ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					// 上面返回true，说明select执行成功，并且已经获取了行锁
					return;
				}

				// 关闭connection、preparedStatement、resultSet
				GracefulClose.close(resultSet, preparedStatement, connection);

				// 如果执行到这里说明数据库里还没有对应的锁名
				// 这里的connection无需设置为手动提交，这么其实没有那么安全，很多线程都会进行insert操作，那么这里通过什么来保证安全呢？
				// --- 唯一索引，针对name属性设置唯一索引

				Connection insertConnection = null;
				PreparedStatement insertPreparedStatement = null;

				try {
					insertConnection = dataSource.getConnection();
					insertPreparedStatement = insertConnection.prepareStatement(INSERT_SQL);
					insertPreparedStatement.setString(1, this.lockName);
					final int rows = insertPreparedStatement.executeUpdate();
					if (rows == 1) {
						// == 1说明更新了一条记录，即创建成功
					}
				} catch (Exception e) {
					// 创建所记录异常
				} finally {
					// 关闭创建所记录的connection
					GracefulClose.close(insertPreparedStatement, insertConnection);
				}

				// 创建完锁记录后，自动提交事务，然后重新进入循环，竞争锁

			}

		} catch (SQLException th) {
			// 获取锁 发生异常
			th.printStackTrace();
		} finally {
			GracefulClose.close(preparedStatement, connection);
		}

	}

	public boolean tryLock(long time, TimeUnit unit) {
		final Future<?> future = threadPoolExecutor.submit(() -> {
			try {
				lock();
				return 1;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		try {
			final Object o = future.get(time, unit);
			if (Objects.isNull(o)) {
				future.cancel(true);
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void unlock() {
		try {
			connection.commit();
			GracefulClose.close(connection);
		} catch (SQLException th) {
			th.printStackTrace();
		}
	}
}
