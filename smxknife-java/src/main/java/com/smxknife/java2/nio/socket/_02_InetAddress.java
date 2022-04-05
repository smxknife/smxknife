package com.smxknife.java2.nio.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author smxknife
 * 2020/10/7
 */
public class _02_InetAddress {
	public static void main(String[] args) {
		// 返回本地主机IP地址和回环地址基本信息
		// test1();

		// 根据主机名获取IP地址
		// test2();

		// 测试不存在的地址
		// test3();

		// 根据主机名获得所有IP地址
		// test4();

		// 根据IP地址byte[] addr获取InetAddress对象
		// test5();

		// 根据主机名IP地址byte[] addr获取InetAddress对象
		// TODO: 并不对Host的有效性进行验证，即使不存在也无所谓
		// test6();

		// 获得全限定主机名和主机名
		// test7();

		// 通过IP获取InetAddress
		// test8();

		// getHostName测试网络连接
		test9();

	}

	private static void test9() {
		InetAddress baiduAddress = null;
		try {
			baiduAddress = InetAddress.getByName("www.smxknife.com");
			System.out.println("建立好连接了...");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// System.out.println("baidu 全限定主机名：" + baiduAddress.getCanonicalHostName());
		// System.out.println("baidu 主机名：" + baiduAddress.getHostName());
		System.out.println("baidu 主机地址：" + baiduAddress.getHostAddress());
	}

	private static void test8() {
		try {
			InetAddress byAddress1 = InetAddress.getByAddress(new byte[] {192 - 256, 168 - 256, 0, 120});
			InetAddress byAddress2 = InetAddress.getByAddress(new byte[] {127, 127, 127, 127});
			// InetAddress byAddress3 = InetAddress.getByAddress(new byte[] {127, 127, 127}); // 不合法
			InetAddress inetAddress4 = InetAddress.getByName("185.199.108.153");
			InetAddress inetAddress5 = InetAddress.getByName("185.199.108");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test7() {
		try {
			// 使用getLocalHost创建InetAddress
			// getCanonicalHostName和getHostName都是本地名称
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("local 全限定主机名：" + inetAddress.getCanonicalHostName());
			System.out.println("local 主机名：" + inetAddress.getHostName());

			System.out.println("-------------");

			InetAddress baiduAddress = InetAddress.getByName("www.baidu.com");
			System.out.println("baidu 全限定主机名：" + baiduAddress.getCanonicalHostName());
			System.out.println("baidu 主机名：" + baiduAddress.getHostName());

			System.out.println("-------------");

			InetAddress ipAddress = InetAddress.getByName("185.199.108.153");
			System.out.println("ip 全限定主机名：" + ipAddress.getCanonicalHostName());
			System.out.println("ip 主机名：" + ipAddress.getHostName());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test6() {
		// ip ： 192.168.0.120
		byte[] ipByte = {192 - 256, 168 - 256, 0, 120};
		try {
			InetAddress myAddress = InetAddress.getByAddress("aaaaaaa", ipByte);
			System.out.println("hostAddress = " + myAddress.getHostAddress());
			System.out.println("hostName = " + myAddress.getHostName());
			System.out.println("class Name = " + myAddress.getClass().getName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test5() {
		// ip ： 192.168.0.120
		byte[] ipByte = {192 - 256, 168 - 256, 0, 120};
		try {
			InetAddress myAddress = InetAddress.getByAddress(ipByte);
			System.out.println("hostAddress = " + myAddress.getHostAddress());
			System.out.println("hostName = " + myAddress.getHostName());
			System.out.println("class Name = " + myAddress.getClass().getName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test4() {
		try {
			InetAddress[] myAddressArray = InetAddress.getAllByName("ShaoYundeMacBook-Pro.local");
			InetAddress[] baiduInetAddressArray = InetAddress.getAllByName("www.baidu.com");
			InetAddress[] ipStringAddressArray = InetAddress.getAllByName("192.168.0.120");

			System.out.println("myAddress..........");
			for (int i = 0; i < myAddressArray.length; i++) {
				System.out.println(myAddressArray[i].getClass().getName() + " " + myAddressArray[i].getHostAddress());
			}

			System.out.println("baiduAddress.........");
			for (int i = 0; i < baiduInetAddressArray.length; i++) {
				System.out.println(baiduInetAddressArray[i].getClass().getName() + " " + baiduInetAddressArray[i].getHostAddress());
			}

			System.out.println("ipStringAddress..........");
			for (int i = 0; i < ipStringAddressArray.length; i++) {
				System.out.println(ipStringAddressArray[i].getClass().getName() + " " + ipStringAddressArray[i].getHostAddress());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test3() {
		try {
			InetAddress inetAddress = InetAddress.getByName("www.23423423423424234234234234234234.com");
			InetAddress ipStringAddress = InetAddress.getByName("192.168.0.999");
			System.out.println(inetAddress.getClass().getName() + " " + inetAddress.getHostAddress());
			System.out.println(ipStringAddress.getClass().getName() + " " + ipStringAddress.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test2() {
		try {
			InetAddress inetAddress = InetAddress.getByName("ShaoYundeMacBook-Pro.local");
			InetAddress baiduInetAddress = InetAddress.getByName("www.baidu.com");

			InetAddress ipStringAddress = InetAddress.getByName("192.168.0.102");
			InetAddress localhost = InetAddress.getByName("localhost");

			System.out.println(localhost.getClass().getName() + " " + localhost.getHostAddress());
			System.out.println(inetAddress.getClass().getName() + " " + inetAddress.getHostAddress());
			System.out.println(baiduInetAddress.getClass().getName() + " " + baiduInetAddress.getHostAddress());
			System.out.println(ipStringAddress.getClass().getName() + " " + ipStringAddress.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void test1() {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println("localHost = " + localHost.getHostAddress());
			System.out.println("localhost name = " + localHost.getHostName());

			InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
			System.out.println("loopbackAddress = " + loopbackAddress.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
