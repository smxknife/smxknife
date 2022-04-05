package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _06_UDP {

	@Test
	public void serverTest1() {
		// DatagramSocket用来发送或接收数据包的套接字
		try(DatagramSocket socket = new DatagramSocket(6666)) {
			byte[] bytes = new byte[12];

			// 传输的数据要放在DatagramPack中
			// 这里第二个参数代表要接收的数据长度
			DatagramPacket packet = new DatagramPacket(bytes, 10);
			socket.receive(packet);

			System.out.println("包（Packet）中数据的长度是：" + packet.getLength());
			System.out.println("数据为：" + new String(packet.getData(), 0, packet.getLength()));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void clientTest1() {
		try(DatagramSocket socket = new DatagramSocket()) {
			socket.connect(new InetSocketAddress("localhost", 6666));

			String content = "1234567890";
			DatagramPacket packet = new DatagramPacket(content.getBytes(), content.length());
			socket.send(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
