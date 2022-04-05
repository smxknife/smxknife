package com.smxknife.java2.generics.demo;

import java.time.LocalDate;

/**
 * @author smxknife
 * 2019-04-17
 */
public class DataInterval extends Pair<LocalDate> {

	public void setSecond(LocalDate date) {
		System.out.println("dataInterval");
	}
}
