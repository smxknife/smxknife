package com.smxknife.drools.demo01;

/**
 * @author smxknife
 * 2020/6/10
 */
public class Product {

	private String name;
	private double prePrice;
	private double realPrice;

	public Product() {}

	public Product(String name, double prePrice) {
		this.name = name;
		this.prePrice = prePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(double prePrice) {
		this.prePrice = prePrice;
	}

	public double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
}
