package com.smxknife.netty.v5.demo07;

import java.io.Serializable;

/**
 * @author smxknife
 * 2018-12-04
 */
public class SubscribeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private int subReqId;

	private String userName;

	private String productName;

	private String phoneNumber;

	private String address;

	@Override
	public String toString() {
		return "SubscribeReq{" +
				"subReqId=" + subReqId +
				", userName='" + userName + '\'' +
				", productName='" + productName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getSubReqId() {
		return subReqId;
	}

	public void setSubReqId(int subReqId) {
		this.subReqId = subReqId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
