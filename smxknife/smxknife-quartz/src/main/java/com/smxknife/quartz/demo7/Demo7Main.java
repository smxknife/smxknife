package com.smxknife.quartz.demo7;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Demo7Main {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group")
				.build();

		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger", "group")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();

		SchedulerListener mySchedulerListener = new MySchedulerListener();
		scheduler.getListenerManager().addSchedulerListener(mySchedulerListener);
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();

		Thread.sleep(5 * 1000);

		scheduler.pauseAll();

		Thread.sleep(5 * 1000);

		scheduler.resumeAll();

		scheduler.getListenerManager().removeSchedulerListener(mySchedulerListener);
	}
}
