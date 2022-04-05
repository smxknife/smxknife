package com.smxknife.java.ex32;

/**
 * @author smxknife
 * 2020/9/10
 */
public class Test {
	public static void main(String[] args) {
		//System.out.println(str2HexStr("1090werewolf"));
		System.out.println((5 + 1) >> 1);
	}

	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}

		int a = 0xFF;
		return sb.toString().trim();
	}
}
