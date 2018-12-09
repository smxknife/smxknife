package com.smxknife.netty.demo06;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2018-12-04
 */
public class PerformTestUserInfo {
	public static void main(String[] args) throws IOException {
		UserInfo userInfo = new UserInfo();
		userInfo.buildUserId(100).buildUserName("Welcome to.");
		int loop = 1000000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(userInfo);
			oos.flush();
			oos.close();
			byte[] bytes = bos.toByteArray();
			bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Jdk serializable time : " + (endTime - startTime) + " ms");
		System.out.println("------------------------");

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			byte[] bytes = userInfo.codeC(byteBuffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("codec serializable time : " + (endTime - startTime) + " ms");
	}
}
