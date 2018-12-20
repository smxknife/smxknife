package com.smxknife.netty.v5.demo06;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author smxknife
 * 2018-12-04
 */
public class TestUserInfo {
	public static void main(String[] args) throws IOException {
		UserInfo userInfo = new UserInfo();
		userInfo.buildUserId(100).buildUserName("welcome to netty");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(userInfo);
		oos.flush();
		oos.close();
		byte[] bytes = bos.toByteArray();
		System.out.println("serializable size " + bytes.length);
		bos.close();
		System.out.println("codec size " + userInfo.codeC().length);
	}
}
