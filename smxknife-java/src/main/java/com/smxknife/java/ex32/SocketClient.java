package com.smxknife.java.ex32;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/9/9
 */
public class SocketClient {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 8888);
		socket.setTcpNoDelay(true);
//		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
//			while (true) {
//				System.out.println("-----------");
//				writer.write("77 00 00 03 20 04 7D E5");
//				writer.flush();
//				TimeUnit.SECONDS.sleep(3);
//			}
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		try (OutputStream os = socket.getOutputStream()){
			while (true) {
				System.out.println("-----------");
				os.write(hexToByteArray2("7700000320047DE5"));
				os.flush();
				TimeUnit.SECONDS.sleep(3);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static byte[] hexToByteArray(String inHex){
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1){
			//奇数
			hexlen++;
			result = new byte[(hexlen/2)];
			inHex="0"+inHex;
		}else {
			//偶数
			result = new byte[(hexlen/2)];
		}
		int j=0;
		for (int i = 0; i < hexlen; i+=2){
			result[j]=hexToByte(inHex.substring(i,i+2));
			j++;
		}
		return result;
	}

	public static byte hexToByte(String inHex){
		return (byte)Integer.parseInt(inHex,16);
	}

	public static byte[] hexToByteArray2(String hex) {
		int len = (hex.length() + 1) >> 1;

		byte[] bytes = new byte[len];
		Arrays.fill(bytes, (byte) 0);

		char[] chars = hex.toCharArray();
		int bLen = len;
		for (int i = chars.length - 2; i >= 0; i-=2) {
			bytes[--bLen] = hexToByte(new String(chars, i, 2));
		}
		return bytes;
	}
}
