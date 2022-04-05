package com.smxknife.flink.ceo.demo.loginfail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author smxknife
 * 2020/8/31
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginEvent {
	private String userId;
	private String flag;
	private long timestamp;
	private String message;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
