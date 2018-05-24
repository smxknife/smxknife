package com.smxknife.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Main {
	public static void main(String[] args) {
		try (Cluster cluster = new ClusterManager().getCluster("127.0.0.1")) {
			Session session = cluster.connect();
			ResultSet rs = session.execute("select release_version from system.local");    // (3)
			Row row = rs.one();
			System.out.println(row.getString("release_version"));

			rs = session.execute("select * from system.peers");
			for (Row row2 : rs) {
				row2.getColumnDefinitions().forEach(column -> {
					System.out.println(column.getName());
				});
			}

			rs = session.execute("select broadcast_address from system.local");
			System.out.printf("broadcast_address is %s", rs.one());
		}
	}
}
