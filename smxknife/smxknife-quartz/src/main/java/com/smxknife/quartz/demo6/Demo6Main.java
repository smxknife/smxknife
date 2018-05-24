package com.smxknife.quartz.demo6;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import java.util.HashSet;
import java.util.Set;

public class Demo6Main {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myjob", "group")
				.build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("/5 * * * * ?"))
				.build();

		CronTrigger trigger2 = TriggerBuilder.newTrigger()
				.withIdentity("trigger2", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("/3 * * * * ?"))
				.build();
		Set<Trigger> triggers = new HashSet<>();
		triggers.add(trigger);
		triggers.add(trigger2);
		scheduler.scheduleJob(jobDetail, triggers, false);

		JobListener myJobListener = new MyJobListener();
		TriggerListener myTriggerListener = new MyTriggerListener();

		scheduler.getListenerManager().addJobListener(myJobListener, EverythingMatcher.allJobs());
		scheduler.getListenerManager().addTriggerListener(myTriggerListener);
		scheduler.start();
	}
}
