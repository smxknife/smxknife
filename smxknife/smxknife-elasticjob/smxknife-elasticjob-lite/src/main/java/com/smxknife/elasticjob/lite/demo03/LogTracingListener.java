package com.smxknife.elasticjob.lite.demo03;

import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.event.JobStatusTraceEvent;
import org.apache.shardingsphere.elasticjob.tracing.listener.TracingListener;
import org.slf4j.Logger;

/**
 * @author smxknife
 * 2020/9/19
 */
public class LogTracingListener implements TracingListener {

	private LogJobEventStorage logJobEventStorage;

	public LogTracingListener(Logger logger) {
		logJobEventStorage = new LogJobEventStorage(logger);
	}

	@Override
	public void listen(JobExecutionEvent jobExecutionEvent) {
		logJobEventStorage.logExecutionEvent(jobExecutionEvent);
	}

	@Override
	public void listen(JobStatusTraceEvent jobStatusTraceEvent) {
		logJobEventStorage.logStatusTraceEvent(jobStatusTraceEvent);
	}
}
