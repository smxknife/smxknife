package com.smxknife.quartz.demo1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Demo1Main {
	public static void main(String[] args) throws SchedulerException {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//		System.out.println("name: " + scheduler.getSchedulerName());
//		System.out.println("instanceId: " + scheduler.getSchedulerInstanceId());
//		System.out.println("meta: " + scheduler.getMetaData());
//		System.out.println("context: " + scheduler.getContext());

		// 声明一个JobDetail，并将MyJob绑定
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "group1")
				.usingJobData("jobSays", "hello")
				.usingJobData("myVal", 3.14f)
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.usingJobData("trigger", "world")
				.usingJobData("myVal", 90)
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInSeconds(2).repeatForever())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();

		try {
			Thread.sleep(20 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("meta2: " + scheduler.getMetaData());
		scheduler.shutdown();

	}
}
