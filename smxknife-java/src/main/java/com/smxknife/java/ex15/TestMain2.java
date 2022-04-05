package com.smxknife.java.ex15;

/**
 * @author smxknife
 * 2018/9/20
 */
public class TestMain2 {
	public static void main(String[] args) throws InterruptedException {
		WeatherData weatherData = new WeatherData();
		weatherData.setNum(10);

		DisplayBoard displayBoard = new DisplayBoard(weatherData);
		OtherBoard otherBoard = new OtherBoard(weatherData);
		int i = 0;
		while (i < 5) {
			weatherData.setNum(i);
			if (weatherData.hasChanged()) {
				System.out.println(">> changed");
				weatherData.notifyObservers();
			}
			Thread.sleep(2000);
			i++;
		}
	}
}
