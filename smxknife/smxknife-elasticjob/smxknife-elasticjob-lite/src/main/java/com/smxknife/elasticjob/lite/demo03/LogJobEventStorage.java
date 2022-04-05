package com.smxknife.elasticjob.lite.demo03;

import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.event.JobStatusTraceEvent;
import org.slf4j.Logger;

/**
 * 之所以会抽象出这一层完全是为了代码层级的清晰，不希望将业务都写在TracingListener层级，在这里可以任意实现，比如分布式相关，输出控制等等。。。。
 * @author smxknife
 * 2020/9/19
 */
public class LogJobEventStorage {

	private Logger logger;

	public LogJobEventStorage(Logger logger) {
		this.logger = logger;
		doSomeLogic();
	}

	private void doSomeLogic() {
		System.out.println("mock some Log logic...");
	}


	public void logExecutionEvent(JobExecutionEvent jobExecutionEvent) {
		System.out.println("------------------: logExecutionEvent");
		logger.info(jobExecutionEvent.toString());
	}

	public void logStatusTraceEvent(JobStatusTraceEvent jobStatusTraceEvent) {
		System.out.println("------------------: logStatusTraceEvent");
		logger.info(jobStatusTraceEvent.toString());
	}
}
