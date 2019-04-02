package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo02_1 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getByName("www.smxknife1.com");
		System.out.println("xxxx");
		System.out.println(byName);

		InetAddress byName2 = InetAddress.getByName("101.111.111.111");
		System.out.println("yyyy");
		System.out.println(byName2);
	}
}
