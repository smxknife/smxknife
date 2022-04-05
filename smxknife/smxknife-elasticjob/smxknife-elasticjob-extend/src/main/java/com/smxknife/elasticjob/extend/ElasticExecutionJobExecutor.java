package com.smxknife.elasticjob.extend;

import lombok.extern.log4j.Log4j;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.api.listener.ShardingContexts;
import org.apache.shardingsphere.elasticjob.executor.JobFacade;
import org.apache.shardingsphere.elasticjob.executor.item.JobItemExecutor;
import org.apache.shardingsphere.elasticjob.executor.item.JobItemExecutorFactory;
import org.apache.shardingsphere.elasticjob.infra.env.IpUtils;
import org.apache.shardingsphere.elasticjob.infra.exception.ExceptionUtils;
import org.apache.shardingsphere.elasticjob.infra.exception.JobExecutionEnvironmentException;
import org.apache.shardingsphere.elasticjob.infra.handler.error.JobErrorHandler;
import org.apache.shardingsphere.elasticjob.infra.handler.error.JobErrorHandlerFactory;
import org.apache.shardingsphere.elasticjob.infra.handler.threadpool.JobExecutorServiceHandlerFactory;
import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.event.JobStatusTraceEvent;
import org.quartz.JobExecutionContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author smxknife
 * 2020/9/22
 */
@Log4j
public class ElasticExecutionJobExecutor implements JobExecutor{

	private final ElasticJob elasticJob;
	private final JobConfiguration jobConfig;
	private final JobFacade jobFacade;
	private final JobItemExecutor jobItemExecutor;
	private final ExecutorService executorService;
	private final JobErrorHandler jobErrorHandler;
	private final Map<Integer, String> itemErrorMessages;

	public ElasticExecutionJobExecutor(ElasticJob elasticJob, JobConfiguration jobConfig, JobFacade jobFacade) {
		this(elasticJob, jobConfig, jobFacade, JobItemExecutorFactory.getExecutor(elasticJob.getClass()));
	}

	public ElasticExecutionJobExecutor(String type, JobConfiguration jobConfig, JobFacade jobFacade) {
		this((ElasticJob)null, jobConfig, jobFacade, JobItemExecutorFactory.getExecutor(type));
	}

	private ElasticExecutionJobExecutor(ElasticJob elasticJob, JobConfiguration jobConfig, JobFacade jobFacade, JobItemExecutor jobItemExecutor) {
		this.elasticJob = elasticJob;
		this.jobConfig = jobConfig;
		this.jobFacade = jobFacade;
		this.jobItemExecutor = jobItemExecutor;
		this.executorService = JobExecutorServiceHandlerFactory.getHandler(jobConfig.getJobExecutorServiceHandlerType()).createExecutorService(jobConfig.getJobName());
		this.jobErrorHandler = JobErrorHandlerFactory.getHandler(jobConfig.getJobErrorHandlerType());
		this.itemErrorMessages = new ConcurrentHashMap(jobConfig.getShardingTotalCount(), 1.0F);
	}

	@Override
	public final void execute(JobExecutionContext context) {
		try {
			this.jobFacade.checkJobExecutionEnvironment();
		} catch (JobExecutionEnvironmentException var5) {
			this.jobErrorHandler.handleException(this.jobConfig.getJobName(), var5);
		}

		ShardingContexts shardingContexts = this.jobFacade.getShardingContexts();
		this.jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(), JobStatusTraceEvent.State.TASK_STAGING, String.format("Job '%s' execute begin.", this.jobConfig.getJobName()));
		if (this.jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())) {
			this.jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(), JobStatusTraceEvent.State.TASK_FINISHED, String.format("Previous job '%s' - shardingItems '%s' is still running, misfired job will start after previous job completed.", this.jobConfig.getJobName(), shardingContexts.getShardingItemParameters().keySet()));
		} else {
			try {
				this.jobFacade.beforeJobExecuted(shardingContexts);
			} catch (Throwable var4) {
				this.jobErrorHandler.handleException(this.jobConfig.getJobName(), var4);
			}

			this.execute(shardingContexts, JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER);

			while(this.jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())) {
				this.jobFacade.clearMisfire(shardingContexts.getShardingItemParameters().keySet());
				this.execute(shardingContexts, JobExecutionEvent.ExecutionSource.MISFIRE);
			}

			this.jobFacade.failoverIfNecessary();

			try {
				this.jobFacade.afterJobExecuted(shardingContexts);
			} catch (Throwable var3) {
				this.jobErrorHandler.handleException(this.jobConfig.getJobName(), var3);
			}

		}
	}

	private void execute(ShardingContexts shardingContexts, JobExecutionEvent.ExecutionSource executionSource) {
		if (shardingContexts.getShardingItemParameters().isEmpty()) {
			this.jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(), JobStatusTraceEvent.State.TASK_FINISHED, String.format("Sharding item for job '%s' is empty.", this.jobConfig.getJobName()));
		} else {
			this.jobFacade.registerJobBegin(shardingContexts);
			String taskId = shardingContexts.getTaskId();
			this.jobFacade.postJobStatusTraceEvent(taskId, JobStatusTraceEvent.State.TASK_RUNNING, "");

			try {
				this.process(shardingContexts, executionSource);
			} finally {
				this.jobFacade.registerJobCompleted(shardingContexts);
				if (this.itemErrorMessages.isEmpty()) {
					this.jobFacade.postJobStatusTraceEvent(taskId, JobStatusTraceEvent.State.TASK_FINISHED, "");
				} else {
					this.jobFacade.postJobStatusTraceEvent(taskId, JobStatusTraceEvent.State.TASK_ERROR, this.itemErrorMessages.toString());
				}

			}

		}
	}

	private void process(ShardingContexts shardingContexts, JobExecutionEvent.ExecutionSource executionSource) {
		Collection<Integer> items = shardingContexts.getShardingItemParameters().keySet();
		if (1 == items.size()) {
			int item = (Integer)shardingContexts.getShardingItemParameters().keySet().iterator().next();
			JobExecutionEvent jobExecutionEvent = new JobExecutionEvent(IpUtils.getHostName(), IpUtils.getIp(), shardingContexts.getTaskId(), this.jobConfig.getJobName(), executionSource, item);
			this.process(shardingContexts, item, jobExecutionEvent);
		} else {
			CountDownLatch latch = new CountDownLatch(items.size());
			Iterator var5 = items.iterator();

			while(var5.hasNext()) {
				int each = (Integer)var5.next();
				JobExecutionEvent jobExecutionEvent = new JobExecutionEvent(IpUtils.getHostName(), IpUtils.getIp(), shardingContexts.getTaskId(), this.jobConfig.getJobName(), executionSource, each);
				if (this.executorService.isShutdown()) {
					return;
				}

				this.executorService.submit(() -> {
					try {
						this.process(shardingContexts, each, jobExecutionEvent);
					} finally {
						latch.countDown();
					}

				});
			}

			try {
				latch.await();
			} catch (InterruptedException var8) {
				Thread.currentThread().interrupt();
			}

		}
	}

	private void process(ShardingContexts shardingContexts, int item, JobExecutionEvent startEvent) {
		this.jobFacade.postJobExecutionEvent(startEvent);
		//log.trace("Job '{}' executing, item is: '{}'.", this.jobConfig.getJobName(), item);

		JobExecutionEvent completeEvent;
		try {
			this.jobItemExecutor.process(this.elasticJob, this.jobConfig, this.jobFacade, shardingContexts.createShardingContext(item));
			completeEvent = startEvent.executionSuccess();
			//log.trace("Job '{}' executed, item is: '{}'.", this.jobConfig.getJobName(), item);
			this.jobFacade.postJobExecutionEvent(completeEvent);
		} catch (Throwable var6) {
			completeEvent = startEvent.executionFailure(ExceptionUtils.transform(var6));
			this.jobFacade.postJobExecutionEvent(completeEvent);
			this.itemErrorMessages.put(item, ExceptionUtils.transform(var6));
			this.jobErrorHandler.handleException(this.jobConfig.getJobName(), var6);
		}

	}

	public void shutdown() {
		this.executorService.shutdown();
	}
}
