package com.smxknife.netty.v5.demo07;

import java.io.Serializable;

/**
 * @author smxknife
 * 2018-12-04
 */
public class SubscribeResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private int subReqId;

	private int respCode;

	private String desc;

	@Override
	public String toString() {
		return "SubscribeResp{" +
				"subReqId=" + subReqId +
				", respCode=" + respCode +
				", desc='" + desc + '\'' +
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

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
