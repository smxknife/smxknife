package com.smxknife.quartz.demo4;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Demo4Main {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myjob", "group")
				.build();

		LocalDateTime now = LocalDateTime.now().plusMinutes(1).withSecond(0).withNano(0);
		Instant start = now.atZone(ZoneId.systemDefault()).toInstant();
		Instant end = now.plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger", "group")
				.forJob("myjob", "group")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
//				.startAt(Date.from(start))
				.startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
//				.endAt(Date.from(end))
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
