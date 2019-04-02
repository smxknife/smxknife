package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo06 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getLocalHost();
		System.out.println(byName);
		System.out.println(byName.getHostName());
		System.out.println(byName.getHostAddress());
	}
}
