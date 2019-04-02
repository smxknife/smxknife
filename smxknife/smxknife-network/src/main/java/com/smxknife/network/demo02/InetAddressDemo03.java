package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo03 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getByName("www.smxknife1.com");
		System.out.println("xxxx");
		System.out.println(byName);
	}
}
