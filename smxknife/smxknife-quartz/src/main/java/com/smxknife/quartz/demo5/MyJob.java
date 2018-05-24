package com.smxknife.quartz.demo5;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getTrigger().getKey() + " " + LocalDateTime.now());
		System.out.println("==============");
	}
}
