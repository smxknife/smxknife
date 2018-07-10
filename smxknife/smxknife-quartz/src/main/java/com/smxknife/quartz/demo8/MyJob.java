package com.smxknife.quartz.demo8;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;
import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getTrigger().getKey() + " " + LocalTime.now());
		Date startTime = nextGivenSecondDate(null, 15);
		System.out.println("nextGivenSecondDate : " + startTime);
		System.out.println("================================");
	}
}
