package com.smxknife.modbus.java;

/**
 * @author smxknife
 * 2019/12/25
 */
public class HexUtil {
	private static final String[] DIGITS_UPPER =
			{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

	public static String toHex(byte value) {
		int high = (value & 0xF0) >>> 4;
		int low = value & 0x0F;
		return DIGITS_UPPER[high] + DIGITS_UPPER[low];
	}

	public static String toHex2(byte value) {
		int high = (value >>> 4) & 0x0F;
		int low = value & 0x0F;
		return DIGITS_UPPER[high] + DIGITS_UPPER[low];
	}


	public static String toHex3(byte value) {
		int tmp = value;
		if (value < 0) {
			tmp = value + 256;
		}
		int high = tmp / 16;
		int low = tmp % 16;
		return DIGITS_UPPER[high] + DIGITS_UPPER[low];
	}

	public static String toHex4(byte value) {
		return String.format("%x", value);
	}
}
