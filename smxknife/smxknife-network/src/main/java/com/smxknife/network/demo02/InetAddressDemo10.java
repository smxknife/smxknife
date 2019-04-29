package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo10 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getByName("183.232.231.173");
		System.out.println("xxxx");
		System.out.println(byName);
		System.out.println(byName.getCanonicalHostName());
	}
}
