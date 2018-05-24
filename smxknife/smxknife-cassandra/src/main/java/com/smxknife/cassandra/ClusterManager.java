package com.smxknife.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Configuration;
import com.datastax.driver.core.Host;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;

public class ClusterManager {

	private static Cluster cluster = null;

	private Factory factory;

	public ClusterManager() {
		factory = new Factory(cluster);
	}

	private class Factory {
		Cluster.Builder builder = null;
		public Factory(Cluster cluster) {
			if (cluster == null) builder = Cluster.builder();
		}
		Cluster build(String point) {
			return builder.addContactPoint(point).build();
		}
	}

	private class Factory2 {
		Cluster.Initializer initializer = new Cluster.Initializer() {
			@Override
			public String getClusterName() {
				return null;
			}

			@Override
			public List<InetSocketAddress> getContactPoints() {
				return null;
			}

			@Override
			public Configuration getConfiguration() {
				return null;
			}

			@Override
			public Collection<Host.StateListener> getInitialListeners() {
				return null;
			}
		};
	}

	public Cluster getCluster(String point) {
		return factory.build(point);
	}
}
