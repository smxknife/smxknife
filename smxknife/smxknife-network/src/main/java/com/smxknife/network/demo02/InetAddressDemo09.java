package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo09 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress byName = InetAddress.getByName("185.199.110.153");
		System.out.println("xxxx");
		System.out.println(byName);
		SecurityManager manager = new SecurityManager();
//		manager.checkConnect("www.smxknife.com", -1);
		System.out.println("---------------------");
		manager.checkConnect("185.199.110.153", -1);
	}
}
