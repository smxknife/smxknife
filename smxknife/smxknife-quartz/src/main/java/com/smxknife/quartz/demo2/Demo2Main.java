package com.smxknife.quartz.demo2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Demo2Main {
	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
					.withIdentity("job", "group")
					.usingJobData("jobSays", "hello")
					.usingJobData("myVal", 10)
					.build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger", "group")
					.usingJobData("trigger", "world")
					.usingJobData("myVal", 20)
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
					.build();

			JobDetail jobDetail2 = JobBuilder.newJob(MyJob.class)
					.withIdentity("job2", "group")
					.usingJobData("jobSays", "hello2")
					.usingJobData("myVal", 100)
					.build();
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group")
					.usingJobData("trigger", "world")
					.usingJobData("myVal", 200)
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
					.build();

			JobDetail jobDetail3 = JobBuilder.newJob(MyJob.class)
					.withIdentity("job3", "group")
					.usingJobData("jobSays", "hello3")
					.usingJobData("myVal", 1000)
					.build();
			Trigger trigger3 = TriggerBuilder.newTrigger()
					.withIdentity("trigger3", "group")
					.usingJobData("trigger", "world")
					.usingJobData("myVal", 2000)
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
					.build();

			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.scheduleJob(jobDetail2, trigger2);
			scheduler.scheduleJob(jobDetail3, trigger3);

			scheduler.start();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
