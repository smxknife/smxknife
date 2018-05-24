package com.smxknife.quartz.demo3;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.DailyCalendar;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Demo3Main {
	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
					.withIdentity("myJob1", "group1")
					.build();

			LocalTime time = LocalTime.now().plusMinutes(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String start = time.format(formatter);
			String end = time.plusMinutes(1).format(formatter);

			DailyCalendar dailyCalendar = new DailyCalendar(start, end);

//			HolidayCalendar calendar = new HolidayCalendar();
//			LocalDateTime excludeTime = LocalDateTime.now().plusSeconds(10);
//
//			calendar.addExcludedDate(Date.from(excludeTime.atZone(ZoneId.systemDefault()).toInstant()));
			scheduler.addCalendar("myHoliday", dailyCalendar, false, false);


			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(2)
							.repeatForever())
					.modifiedByCalendar("myHoliday")
					.build();

			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
