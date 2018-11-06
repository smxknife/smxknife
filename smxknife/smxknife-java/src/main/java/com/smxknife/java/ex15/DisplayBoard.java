package com.smxknife.java.ex15;

import java.util.Observable;
import java.util.Observer;

/**
 * @author smxknife
 * 2018/9/20
 */
public class DisplayBoard implements Observer {

	private WeatherData weatherData;

	public DisplayBoard(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("display observers count : " + o.countObservers());
		System.out.println("display args : " + arg);

		if (o instanceof WeatherData) {
			WeatherData data = (WeatherData) o;
			System.out.println("display pull args : " + data.getNum());
		}

	}
}
