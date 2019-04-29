package com.smxknife.network.demo03;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author smxknife
 * 2019-02-13
 */
public class NetworkInterfaceDemo {
	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface inter = interfaces.nextElement();
			System.out.println(inter);
			System.out.println(inter.getIndex());
			System.out.println(inter.getDisplayName());
			System.out.println(inter.getName());
			System.out.println(inter.getParent());
			byte[] hardwareAddress = inter.getHardwareAddress();
			for (int i = 0; hardwareAddress != null && i < hardwareAddress.length; i++) {
				System.out.print(hardwareAddress[i] + ", ");
			}
			System.out.println();
			Enumeration<InetAddress> inetAddresses = inter.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				System.out.println(inetAddresses.nextElement());
			}
			System.out.println(inter.getInterfaceAddresses());
			System.out.println(inter.getMTU());
			System.out.println(inter.getSubInterfaces());
			System.out.println("====");
		}
		System.out.println("--------");
		NetworkInterface local = NetworkInterface.getByName("local");
		System.out.println(local);
	}
}
