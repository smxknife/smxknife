package com.smxknife.network.demo02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2019-02-12
 */
public class InetAddressDemo06 {
	public static void main(String[] args) throws UnknownHostException {
		byte[] addr = {(byte) 185, (byte) 199, 110, (byte) 153};
		InetAddress byName = InetAddress.getByAddress(addr);
		System.out.println(byName);
		System.out.println(byName.getHostName());
		System.out.println(byName.getHostAddress());
	}
}
