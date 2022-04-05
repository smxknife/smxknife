package com.smxknife.java.ex15;

import java.util.Observable;

/**
 * @author smxknife
 * 2018/9/20
 */
public class WeatherData extends Observable {

	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		this.setChanged();
	}
}
