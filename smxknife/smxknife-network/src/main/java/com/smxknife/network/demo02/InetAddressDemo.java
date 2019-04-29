package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] allByName = InetAddress.getAllByName("www.smxknife.com");
		Stream.of(allByName).forEach(System.out::println);

		System.out.println("================");
		InetAddress byName = InetAddress.getByName("www.smxknife.com");
		System.out.println(byName);
		System.out.println(byName.getHostAddress());
		for (int i = 0; i < byName.getAddress().length; i++) {
			System.out.print(byName.getAddress()[i]);
			System.out.print(", ");
		}
		System.out.println();
		System.out.println(byName.getHostName());
		System.out.println(byName.getCanonicalHostName());

		System.out.println("================");
		InetAddress byName1 = InetAddress.getByName("185.199.111.153");
		System.out.println(byName1);

		System.out.println("================");
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost);

		System.out.println("================");
		InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
		System.out.println(loopbackAddress);
	}
}
