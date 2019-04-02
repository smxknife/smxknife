package com.smxknife.network.demo02;

import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo09 {
	public static void main(String[] args) throws UnknownHostException {
		String ttl = System.getProperty("networkaddress.cache.ttl");
		String negative = System.getProperty("networkaddress.cache.negative.ttl");
		System.out.println(ttl);
		System.out.println(negative);
	}
}
