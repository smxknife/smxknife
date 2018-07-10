package com.smxknife.quartz.demo8;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;

public class Demo8Main {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "group")
				.build();

		System.out.println("now : " + LocalDateTime.now());
		Date startTime = nextGivenSecondDate(null, 15);
		System.out.println("start : " + startTime);

		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger", "group")
				.startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
