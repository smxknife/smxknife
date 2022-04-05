package com.smxknife.java.ex27;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.util.Date;

/**
 * @author smxknife
 * 2019-06-01
 */
public class Demo8 {
	public static void main(String[] args) {
		LocalDateTime dateTime = LocalDateTime.now();

		System.out.println(dateTime);
		Instant now = Instant.now();
		Date.from(now);
		System.out.println("now = " + now);

		System.out.println(Month.FEBRUARY.length(true));
		System.out.println(Month.AUGUST.firstDayOfYear(true));
		System.out.println("-------------");
		System.out.println(dateTime.query(TemporalQueries.chronology()));
		System.out.println(dateTime.query(TemporalQueries.localDate()));
		System.out.println(dateTime.query(TemporalQueries.localTime()));
		System.out.println(dateTime.query(TemporalQueries.offset()));
		System.out.println(dateTime.query(TemporalQueries.precision()));
		System.out.println(dateTime.query(TemporalQueries.zone()));
		System.out.println(dateTime.query(TemporalQueries.zoneId()));

		System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));

	}
}
