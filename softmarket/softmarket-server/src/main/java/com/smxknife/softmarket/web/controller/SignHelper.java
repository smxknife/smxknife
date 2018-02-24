package com.smxknife.softmarket.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
public class SignHelper {

	@Value("{wechat.token}")
	String token;

	public boolean checkSignature(String signature, String timestamp, String notice) {
		String[] arrays = new String[] {token, timestamp, notice};
		Arrays.sort(arrays);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arrays.length; i++) {
			builder.append(arrays[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行 sha1 加密
			byte[] digest = md.digest(builder.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		builder = null;
		// 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 */
	private String byteToStr(byte[] byteArray) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			builder.append(byteToHexStr(byteArray[i]));
		}
		return builder.toString();
	}

	/**
	 * 将字节转换为十六进制字符串
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
