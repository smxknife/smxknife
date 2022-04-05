package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _06_UDP_Broadcast {

	// TODO: 如果要测试广播，需要两台机器，这里无法测试
	// TODO: 机器A
	@Test
	public void server1Test() {
		try(DatagramSocket socket = new DatagramSocket(6666)) {
			byte[] bytes = new byte[12];
			DatagramPacket packet = new DatagramPacket(bytes, 10);
			socket.receive(packet);
			System.out.println("数据为：" + new String(packet.getData(), 0, packet.getLength()));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: 如果要测试广播，需要两台机器，这里无法测试
	// TODO: 机器B
	@Test
	public void server2Test() {
		try(DatagramSocket socket = new DatagramSocket(6666)) {
			byte[] bytes = new byte[12];
			DatagramPacket packet = new DatagramPacket(bytes, 10);
			socket.receive(packet);
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


			// TODO: 注意点
			socket.setBroadcast(true);
			// TODO: 注意点
			socket.connect(InetAddress.getByName("192.168.32.120"), 6666);


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
