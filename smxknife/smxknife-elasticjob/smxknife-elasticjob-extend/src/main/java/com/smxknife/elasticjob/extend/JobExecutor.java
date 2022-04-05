package com.smxknife.elasticjob.extend;

import org.quartz.JobExecutionContext;

/**
 * @author smxknife
 * 2020/9/22
 */
public interface JobExecutor {
	/**
	 * with quartz ExecutionContext
	 * @param context
	 */
	void execute(JobExecutionContext context);
}
