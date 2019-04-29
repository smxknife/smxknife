package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo04 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getByName("185.199.110.153");
		System.out.println("xxxx");
		System.out.println(byName);
		System.out.println(byName.getHostName());
		System.out.println(byName.getHostAddress());
	}
}
