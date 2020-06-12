package com.smxknife.flink.demo01;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author smxknife
 * 2020/4/24
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	private long accountId;
	@Getter
	private double val;
	public Transaction(long accountId) {
		this(accountId, -1);
	}

	public Transaction(long accountId, double val) {
		this.accountId = accountId;
		this.val = val;
	}
}
