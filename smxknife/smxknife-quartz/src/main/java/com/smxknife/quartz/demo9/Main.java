package com.smxknife.quartz.demo9;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/2/6
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

			int interval = 300;

			for (int i = 0; i < 100; i++) {
				String jobName = "mockJob_" + i;

				JobDetail jobDetail = JobBuilder.newJob(MockJob.class).withIdentity(jobName).build();
				SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(jobName)
//						.startAt(randomDelayTime(interval))
						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).repeatForever())
						.build();

				scheduler.scheduleJob(jobDetail, trigger);
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		TimeUnit.HOURS.sleep(1);
	}

	private static Date randomDelayTime(int interval) {
		long nextLong = ThreadLocalRandom.current().nextLong(interval * 1000);
		return new Date(System.currentTimeMillis() + nextLong);
	}
}
