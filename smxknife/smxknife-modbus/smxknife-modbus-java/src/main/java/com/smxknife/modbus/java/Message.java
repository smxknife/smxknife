package com.smxknife.modbus.java;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019/12/25
 */
public class Message {
	// byte[0]   byte[1]   消息no
	// byte[2]   byte[3]   标识
	// byte[4]   byte[5]   byte[5]之后的数据长度
	// byte[6]             站号或数据地址
	// byte[7]             功能码
	// byte[8]   byte[9]   起始地址
	// byte[10]  byte[11]  读取数据长度

	/**
	 * 报文头
 	 */
	private Header header;

	/**
	 * byte[7]
	 * 功能码
	 */
	private FunctionCode code;

	/**
	 * 起始地址byte[8] byte[9]
	 */
	private int start;

	/**
	 * 指定想读取的数据长度byte[10] byte[11]
	 */
	private int offset;

	public Message(FunctionCode code, int offset) {
		this((short) 0, code, offset);
	}

	public Message(short address, FunctionCode code, int offset) {
		this(address, code, code.getStart(), offset);
	}

	public Message(short address, FunctionCode code, int start, int offset) {
		this.header = new Header();
		this.header.address = address;
		this.code = code;
		this.start = start;
		this.offset = offset;
	}

	public byte[] getBytes() {
		List<String> hexList = new ArrayList<>();
		// no 处理
		int2Hex2(hexList, this.header.no);
		// modbus标识
		int2Hex2(hexList, 0);
		// byte 5 之后
		int2Hex2(hexList, this.header.contentLength);
		// 站号
		int2Hex1(hexList, this.header.address);
		// 功能码
		int2Hex1(hexList, this.code.getCode());
		// 起始地址
		int2Hex2(hexList, this.start);
		// 获取长度
		int2Hex2(hexList, this.offset);
		return hexList.stream().collect(Collectors.joining(" ")).getBytes();
	}

	private void int2Hex1(List<String> hexList, int number) {
		String num = Integer.toHexString(number);
		if (num.length() == 1) {
			hexList.add("0" + num);
		} else {
			hexList.add(num);
		}
	}

	private void int2Hex2(List<String> hexList, int number) {
		String num = Integer.toHexString(number);
		if (num.length() == 1) {
			hexList.add("00");
			hexList.add("0" + num);
		} else if (num.length() == 2) {
			hexList.add("00");
			hexList.add(num);
		} else if (num.length() == 3) {
			hexList.add("0" + num.substring(0, 1));
			hexList.add(num.substring(1));
		} else {
			hexList.add(num.substring(0, 2));
			hexList.add(num.substring(2));
		}
	}

	private static class Header {

		private static int messageNo = 0;

		private static synchronized int nextMessageNo() {
			return messageNo++;
		}

		public Header() {
			this.no = nextMessageNo();
		}

		/**
		 * 消息号byte[0] byte[1]
		 * short正好可以表示两个字节(但是这里不能用short的原因是最高位为符号位，这样short的表示范围将不足，无法表示0xFF 0xFF)
		 * 随便指定，服务器返回的数据的前两个字和这个一样
		 * 为此次通信事务处理标识符，一般每次通信之后将被要求加1以区别不同的通信数据报文
		 */
		private int no;

		/**
		 * modbus标识byte[2]  byte[3]，强制为0即可
		 * 协议标识符，00 00为modbus协议
		 */
		private final short flag = 0;

		/**
		 * byte[4]  byte[5]
		 * 指示排在byte[5]后面所有字节的个数，也就是总长度-6
		 * 为数据长度，用来指示接下来数据的长度，单位字节
		 */
		private int contentLength;

		/**
		 * 站号或数据地址 byte[6]
		 * 00  -- FF 都可以，随便指定
		 * 这里不能用byte还是因为符号的原因
		 */
		private short address = 0;
	}

	public enum FunctionCode {
		READ_COIL_STATUS(0x01, 1, 9999), // 读取线圈状态
		READ_DISCRETE_INPUT_STATUS(0x02, 10001, 19999), // 读离散输入状态
		READ_HOLDING_REGISTER(0x03, 40001, 49999), // 读取保持寄存器
		READ_INPUT_REGISTER(0x04, 30001, 39999), //读输入寄存器
		WRITE_SINGLE_COIL(0x05, 1, 9999), //写单个线圈
		WRITE_SINGLE_HOLDING_REGISTER(0x06, 40001, 49999), //写单个保持寄存器
		WRITE_MULTI_COIL(0x15, 1, 9999), //写多个线圈
		WRITE_MULTI_HOLDING_REGISTER(0x16, 40001, 49999); //写多个保持寄存器

		private int code;
		@Getter
		private int start;
		@Getter
		private int end;
		FunctionCode(int code, int start, int end) {
			this.code = code;
			this.start = start;
			this.end = end;
		}

		public byte getCode() {
			return (byte) this.code;
		}
	}
}
