package com.smxknife.elasticjob.lite.demo03;

import org.apache.shardingsphere.elasticjob.tracing.exception.TracingConfigurationException;
import org.apache.shardingsphere.elasticjob.tracing.listener.TracingListener;
import org.apache.shardingsphere.elasticjob.tracing.listener.TracingListenerConfiguration;
import org.slf4j.Logger;

/**
 * @author smxknife
 * 2020/9/19
 */
public class LogTracingListenerConfiguration implements TracingListenerConfiguration<Logger> {

	@Override
	public TracingListener createTracingListener(Logger logger) throws TracingConfigurationException {
		return new LogTracingListener(logger);
	}

	@Override
	public String getType() {
		return "LOG";
	}
}
