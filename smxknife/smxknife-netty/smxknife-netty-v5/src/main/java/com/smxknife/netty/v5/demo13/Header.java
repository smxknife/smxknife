package com.smxknife.netty.v5.demo13;

import java.util.HashMap;
import java.util.Map;

/**
 * Netty协议消息头定义
 * @author smxknife
 * 2018-12-11
 */
public class Header {

	/**
	 * 消息的校验码，由三部分组成
	 * 1）0xabef：固定值表明是netty协议消息，2个字节
	 * 2）主版本号：1~255，1个字节
	 * 3）次版本号：1~255，1个字节
	 */
	private int crcCode = 0xabef0101;

	/**
	 * 消息的长度，整个消息，包括消息头和消息体
	 */
	private int length;

	/**
	 * 集群节点内全局唯一，由会话id生成器生成
	 */
	private long sessionID;

	/**
	 * 0：业务请求消息
	 * 1：业务响应消息
	 * 2：业务ONE_WAY消息（既是请求又是响应消息）
	 * 3：握手请求消息
	 * 4：握手应答消息
	 * 5：心跳请求消息
	 * 6：心跳应答消息
	 */
	private byte type;

	/**
	 * 消息优先级：0~255
	 */
	private byte priority;

	/**
	 * 可选字段，用于扩展消息头
	 */
	private Map<String, Object> attachment = new HashMap<>();

	public final int getCrcCode() {
		return crcCode;
	}

	public final void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}

	public final int getLength() {
		return length;
	}

	public final void setLength(int length) {
		this.length = length;
	}

	public final long getSessionID() {
		return sessionID;
	}

	public final void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}

	public final byte getType() {
		return type;
	}

	public final void setType(byte type) {
		this.type = type;
	}

	public final byte getPriority() {
		return priority;
	}

	public final void setPriority(byte priority) {
		this.priority = priority;
	}

	public final Map<String, Object> getAttachment() {
		return attachment;
	}

	public final void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "Header {" +
				"crcCode=" + crcCode +
				", length=" + length +
				", sessionID=" + sessionID +
				", type=" + type +
				", priority=" + priority +
				", attachment=" + attachment +
				'}';
	}

	public enum MessageType {
		/**
		 * -1:失败
		 * 0：业务请求消息
		 * 1：业务响应消息
		 * 2：业务ONE_WAY消息（既是请求又是响应消息）
		 * 3：握手请求消息
		 * 4：握手应答消息
		 * 5：心跳请求消息
		 * 6：心跳应答消息
		 */
		FAIL((byte)-1),
		LOGIC_REQ((byte)0),
		LOGIC_RESP((byte)1),
		LOGIC_ONE_WAY((byte)2),
		LOGIN_REQ((byte)3),
		LOGIN_RESP((byte)4),
		HEART_BRAT_REQ((byte)5),
		HEART_BRAT_RESP((byte)6);

		byte code;
		MessageType(byte code) {
			this.code = code;
		}

		public byte getCode() {
			return code;
		}
	}
}
