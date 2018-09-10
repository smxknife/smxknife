package com.smxknife.springboot.ebean.domain;

import io.ebean.annotation.Sql;

import javax.persistence.Entity;

@Entity
@Sql
public class Order {

	private long orderId;

	private int userId;

	private String status;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("id: %s, user_id: %s, status: %s", orderId, userId, status);
	}

}
