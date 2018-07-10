package com.smxknife.quartz.demo7;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.DateBuilder.nextGivenSecondDate;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getTrigger().getKey());

		Date runTime = evenMinuteDate(new Date());

		Date startTime = nextGivenSecondDate(null, 15);
	}
}
