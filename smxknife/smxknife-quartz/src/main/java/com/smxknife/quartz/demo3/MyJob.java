package com.smxknife.quartz.demo3;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			System.out.println("======== " + jobExecutionContext.getJobDetail().getKey() + " [ " + LocalTime.now() + " ] ");
		} catch (Exception e) {

		}
	}
}
