package com.smxknife.netty.demo06;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2018-12-04
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private int userId;

	public UserInfo buildUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public UserInfo buildUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public byte[] codeC() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byte[] value = this.userName.getBytes();
		byteBuffer.putInt(value.length);
		byteBuffer.put(value);
		byteBuffer.putInt(this.userId);
		byteBuffer.flip();
		value = null;
		byte[] bytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(bytes);
		return bytes;
	}

	public byte[] codeC(ByteBuffer byteBuffer) {
		byteBuffer.clear();
		byte[] value = this.userName.getBytes();
		byteBuffer.putInt(value.length);
		byteBuffer.put(value);
		byteBuffer.putInt(this.userId);
		byteBuffer.flip();
		value = null;
		byte[] bytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(bytes);
		return bytes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
