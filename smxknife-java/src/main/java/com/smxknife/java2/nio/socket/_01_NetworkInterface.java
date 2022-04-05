package com.smxknife.java2.nio.socket;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @author smxknife
 * 2020/10/7
 */
public class _01_NetworkInterface {
	public static void main(String[] args) {
		try {
			// NetworkInterface表示由名称和分配给此接口的IP地址列表组成的网络接口，该类提供了访问网卡的相关信息，如网卡名称、IP地址、子网掩码
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				System.out.println("网卡名称：" + networkInterface.getName());
				System.out.println("网卡设备显示名称：" + networkInterface.getDisplayName());
				System.out.println("网络接口索引：" + networkInterface.getIndex());
				System.out.println("是否已经开启并运行：" + networkInterface.isUp());
				System.out.println("是否为回调接口：" + networkInterface.isLoopback());
				System.out.println("是否为虚拟接口：" + networkInterface.isVirtual());

				// MAC地址
				byte[] hardwareAddress = networkInterface.getHardwareAddress();
				if (Objects.nonNull(hardwareAddress)) {
					System.out.println("网卡硬件地址（MAC）：" + mac(hardwareAddress));
				} else {
					System.out.println("网卡硬件地址（MAC）：null");
				}
				// 最大传输单元:
				/**
				 * 大多数网卡为1500字节，在IPV6协议中，范围是1280~65535
				 * 如果MTU设置大值，传输速度很快，因为发送的包少了，但是延迟很大，因为对方需要一点点的处理数据
				 * 如果MTU设置小值，传输速度慢，因为发送的数据包多了
				 * 建议不要随意修改网卡的MTU值，有可能造成网络传输数据故障，发生丢包现象
				 * 网卡禁用时，MTU可能为-1
				 */
				System.out.println("最大传输单元：" + networkInterface.getMTU());


				// IP地址
				System.out.println("网卡IP地址列表：");
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				if (Objects.nonNull(inetAddresses)) {
					while (inetAddresses.hasMoreElements()) {
						InetAddress inetAddress = inetAddresses.nextElement();
						System.out.printf("     %s\r\n", inetAddress);
						// 下面这三个涉及到IP解析，速度非常慢，先注释掉
						// System.out.println("IP地址完全限定域名：" + inetAddress.getCanonicalHostName()); // 获取此IP的完全限定域名（主机名 + 全路径），另外因为每次都需要与DNS进行通信，非常耗时，暂且注掉
						System.out.println("IP地址字符串：" + inetAddress.getHostAddress()); // 返回IP地址字符串
						System.out.println("IP地址主机域名：" + inetAddress.getHostName()); // 获取IP的主机名
					}
				}

				Optional.ofNullable(networkInterface.getInterfaceAddresses()).ifPresent(list -> list.forEach(ifa -> {
					System.out.println(ifa);
					System.out.println(ifa.getBroadcast());
				}));

				// 子接口的作用是在不添加新的物理网卡的基础上，基于原有的网络接口设备再创建出一个虚拟的网络接口设备进行通信。这个虚拟的网络接口可以理解成一个由软件模拟的网卡
				// windows不支持子接口，linux支持
				// isVirtual：判断当前的网络接口是否为"虚拟子接口"
				// 虚拟子接口的名称是父网络接口的名称加上冒号，再加上表示该子接口的编号
				// 虚拟接口也是由软件模拟的网络接口，没有物理设备，没有父接口
				// 虚拟接口不一定就是虚拟子接口，而虚拟子接口一定是虚拟接口
				System.out.println("子接口：");
				Enumeration<NetworkInterface> subInterfaces = networkInterface.getSubInterfaces();
				if (Objects.nonNull(subInterfaces)) {
					while (subInterfaces.hasMoreElements()) {
						NetworkInterface subNetworkInterface = subInterfaces.nextElement();
						System.out.println("虚拟子接口：isVirtual " + subNetworkInterface.isVirtual());
						System.out.println("虚拟子接口名称：" + subNetworkInterface.getName());
					}
				}


				System.out.println("-------------------------------------------");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private static String ip(byte[] ipAddress) {
		StringJoiner joiner = new StringJoiner(".");
		for (int i = 0; i < ipAddress.length; i++) {
			int item = 0;
			byte element = ipAddress[i];
			if (element >= 0) {
				item = element;
			} else {
				item = 256 + element;
			}
			joiner.add(String.valueOf(item));
		}
		return joiner.toString();
	}

	private static String mac(byte[] hardwareAddress) {
		StringJoiner joiner = new StringJoiner(":");
		for (int i = 0; i < hardwareAddress.length; i++) {
			int item = 0;
			byte element = hardwareAddress[i];
			if (element > 0) {
				item = element;
			} else {
				item = 256 + element;
			}
			joiner.add(Integer.toHexString(item).toUpperCase());
		}
		return joiner.toString();
	}
}
