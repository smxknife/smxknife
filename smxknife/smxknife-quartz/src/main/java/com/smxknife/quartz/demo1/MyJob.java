package com.smxknife.quartz.demo1;

import org.quartz.*;

public class MyJob implements Job {

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		System.out.println(jobExecutionContext);
		System.out.println(Thread.currentThread().getName() + " - Hello, MyJob is executing ------ ");

		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		JobKey key = jobDetail.getKey();
		System.out.printf("myjob key is %s\r\n", key);
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String jobSays = jobDataMap.getString("jobSays");
		float myVal = jobDataMap.getFloat("myVal");
		System.out.printf("jobSays=%s, myVal=%s\r\n", jobSays, myVal);

		Trigger trigger = jobExecutionContext.getTrigger();
		System.out.println("-------------------");
		System.out.printf("trigger's jobKey %s\r\n", trigger.getJobKey());
		System.out.printf("trigger's startTime %s\r\n", trigger.getStartTime());
		System.out.printf("trigger's endTime %s\r\n", trigger.getEndTime());
		System.out.println("-------------------");
		JobDataMap jobDataMap1 = trigger.getJobDataMap();
		System.out.printf("trigger=%s, myVal=%s\r\n",
				jobDataMap1.getString("trigger"),
				jobDataMap1.get("myVal"));

		JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
		System.out.printf("jobSays=%s, trigger=%s, myVal=%s\r\n", mergedJobDataMap.get("jobSays"), mergedJobDataMap.get("trigger"), mergedJobDataMap.get("myVal"));
		System.out.println("=========================");
	}
}
