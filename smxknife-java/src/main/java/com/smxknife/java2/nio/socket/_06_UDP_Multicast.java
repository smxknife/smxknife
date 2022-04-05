package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author smxknife
 * 2020/10/13
 */
public class _06_UDP_Multicast {

	@Test
	public void server1Test() {

		try(MulticastSocket socket = new MulticastSocket(6666)) {
			socket.joinGroup(InetAddress.getByName("224.0.0.5"));
			byte[] bytes = new byte[12];
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
			socket.receive(packet);
			System.out.println("数据为：" + new String(packet.getData(), 0, packet.getLength()));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void server2Test() {

		try(MulticastSocket socket = new MulticastSocket(6666)) {
			socket.joinGroup(InetAddress.getByName("224.0.0.5"));
			byte[] bytes = new byte[12];
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
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
		try(MulticastSocket socket = new MulticastSocket()) {
			String content = "1234567890";
			DatagramPacket packet = new DatagramPacket(content.getBytes(),
					content.length(), InetAddress.getByName("224.0.0.5"), 6666);
			socket.send(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
