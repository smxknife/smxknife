package com.smxknife.quartz.demo6;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
	@Override
	public String getName() {
		return "myJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println(context.getTrigger().getKey() + "====== jobToBeExecuted");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println(context.getTrigger().getKey() + "====== jobExecutionVetoed");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		System.out.println(context.getTrigger().getKey() + "====== jobWasExecuted");
	}
}
