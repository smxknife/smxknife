package com.smxknife.quartz.demo5;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;

public class Demo5Main {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//				.withIdentity("myjob", "group")
				.build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("/7 * * 22W * ?"))
				.forJob(jobDetail)
				.build();

		CronTrigger trigger2 = TriggerBuilder.newTrigger()
				.withIdentity("trigger2", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("*/7 * * 21W * ?"))
				.forJob(jobDetail)
				.build();

		CronTrigger trigger3 = TriggerBuilder.newTrigger()
				.withIdentity("trigger3", "group")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/7 * * 23W * ?"))
				.forJob(jobDetail)
				.build();

		Set<Trigger> triggers = new HashSet<>();
		triggers.add(trigger);
		triggers.add(trigger2);
		triggers.add(trigger3);

		scheduler.scheduleJob(jobDetail, triggers, false);
//		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
