package com.smxknife.netty.v5.demo10;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2018-12-10
 */
@Getter
@Setter
public class Order {
	private long orderNumber;
	private Customer customer;
	private Address billTo;
	private Shipping shipping;
	private Address shipTo;
	private Float total;
}
