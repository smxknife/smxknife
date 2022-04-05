package com.smxknife.elasticjob.extend;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author smxknife
 * 2020/9/22
 */
public class ExecutionJob implements Job {
	private JobExecutor jobExecutor;

	public ExecutionJob(JobExecutor jobExecutor) {
		this.jobExecutor = jobExecutor;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		this.jobExecutor.execute(jobExecutionContext);
	}
}
