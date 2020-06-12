package com.smxknife.modbus.java;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/25
 */
public class _Main {
	public static void main(String[] args) throws IOException {
		ModbusReader reader = new ModbusReader("localhost", 1502);
		Message message = new Message(Message.FunctionCode.READ_HOLDING_REGISTER, 10);
		reader.read(message);
	}
}
