package com.smxknife.cache.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author smxknife
 * 2020/8/15
 */
public class _06_Cluster {
	public static void main(String[] args) {
//		HostAndPort node1 = new HostAndPort("62.234.100.39", 17379);
//		HostAndPort node2 = new HostAndPort("62.234.100.39", 17380);
//		HostAndPort node3 = new HostAndPort("62.234.100.39", 17381);
		HostAndPort node4 = new HostAndPort("62.234.100.39", 17382);
		HostAndPort node5 = new HostAndPort("62.234.100.39", 17383);
		HostAndPort node6 = new HostAndPort("62.234.100.39", 17384);
		Set<HostAndPort> clusterNodes = new HashSet<>();
//		clusterNodes.add(node1);
//		clusterNodes.add(node2);
//		clusterNodes.add(node3);
		clusterNodes.add(node4);
		clusterNodes.add(node5);
		clusterNodes.add(node6);
		JedisCluster jedisCluster = new JedisCluster(clusterNodes);
		String set = jedisCluster.set("hello", "world");

		System.out.println(set);
		String hello = jedisCluster.get("hello");
		System.out.println(hello);


		Long hello1 = jedisCluster.del("hello");
		System.out.println(hello1);
//		jedisCluster.close();
	}
}
