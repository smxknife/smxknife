package com.smxknife.java.ex15;

import java.util.Observable;
import java.util.Observer;

/**
 * @author smxknife
 * 2018/9/20
 */
public class OtherBoard implements Observer {

	private WeatherData weatherData;

	public OtherBoard(WeatherData weatherData) {
		this.weatherData = weatherData;
		this.weatherData.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("other arg : " + arg);
	}
}
