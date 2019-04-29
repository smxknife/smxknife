package com.smxknife.network.demo02;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo02_1 {
	public static void main(String[] args) throws IOException {

		InetAddress byName2 = InetAddress.getByName("101.111.111.111");
		System.out.println("yyyy");
		System.out.println(byName2);
		System.out.println(byName2.isReachable(10000));
		byName2.getCanonicalHostName();
	}
}
