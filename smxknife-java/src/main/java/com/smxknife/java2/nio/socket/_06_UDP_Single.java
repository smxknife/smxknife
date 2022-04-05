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
public class _06_UDP_Single {

	@Test
	public void server1Test() {
		try(DatagramSocket socket = new DatagramSocket(6666)) {
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
