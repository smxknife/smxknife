package com.smxknife.modbus.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author smxknife
 * 2019/12/25
 */
public class ModbusReader {
	private Socket socket;

	public ModbusReader(String host, int port) throws IOException {
		socket = new Socket(host, port);
		socket.setTcpNoDelay(true);
		socket.setKeepAlive(true);
		socket.setReceiveBufferSize(1024);
		socket.setSendBufferSize(1024);
	}

	public byte[] read(Message message) {
		try(OutputStream os = socket.getOutputStream();
		    InputStream is = socket.getInputStream();) {
//			os.write(new byte[] { 0x00, 0x07, 0x00, 0x00, 0x00, 0x06, 0x01, 0x03,0x00, 0x00, 0x00, 0x08 });
			os.write(message.getBytes());
			os.flush();
			byte[] buffer = new byte[12];
			is.read(buffer);
			for (byte b : buffer) {
				System.out.print(b);
				System.out.print(" ");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
