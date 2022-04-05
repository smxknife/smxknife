package com.smxknife.energy.collector.core.driver;

import com.smxknife.energy.collector.core.driver.datasource.Datasource;
import com.smxknife.energy.collector.core.driver.datasource.DatasourceHandler;
import com.smxknife.energy.collector.core.driver.sink.SinkHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author smxknife
 * 2021/5/12
 */
@Slf4j
@Getter
public class Driver implements Lifecycle {

	private String uid;
	private Datasource dataSource;
	private DatasourceHandler handler;
	private Scheduler scheduler;
	private JobDetail jobDetail;
	private CronTrigger trigger;

	private List<SinkHandler> sinkHandlers;

	public Driver(DatasourceHandler handler, List<SinkHandler> sinkHandlers) {
		this.sinkHandlers = sinkHandlers;
		this.handler = handler;
		this.dataSource = this.handler.getDatasource();
		this.uid = this.handler.getDatasource().getUid();
	}

	@Override
	public void init() {
		this.handler.connect();
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("this", this);
		jobDetail = JobBuilder.newJob(CronJob.class)
				.withIdentity(dataSource.getUid(), dataSource.getHost())
				.setJobData(jobDataMap)
				.build();

		trigger = TriggerBuilder.newTrigger()
				.withIdentity(dataSource.getUid(), dataSource.getHost())
				.withSchedule(CronScheduleBuilder.cronSchedule(dataSource.getCron()))
				.forJob(jobDetail)
				.build();

	}

	@Override
	public void start() {
		try {
			this.scheduler.scheduleJob(this.jobDetail, this.trigger);
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void stop() {
		try {
			this.scheduler.pauseAll();
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void terminate() {
		try {
			this.scheduler.deleteJob(this.jobDetail.getKey());
			this.dataSource = null;
			this.jobDetail = null;
			this.trigger = null;
			this.scheduler = null;
			this.handler.disconnect();
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static class CronJob implements Job {

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
			final Driver driver = (Driver) jobDataMap.get("this");
			driver.handler.handle(LocalDateTime.now(), null, driver.sinkHandlers);
		}
	}

}
