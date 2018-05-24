package com.smxknife.quartz.demo2;

import org.quartz.*;

import java.time.LocalTime;

//@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob implements Job {

	private String jobSays;
	private Object myVal;
	private String trigger;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.printf("the job is isRecovering ? %s\r\n", jobExecutionContext.isRecovering());
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		String key = jobDetail.getKey().getName();
		System.out.printf("jobKey=%s, *** jobSays=%s, trigger=%s, myVal=%s\r\n", key, jobSays, trigger, myVal);
		JobDataMap jobDataMap = jobDetail.getJobDataMap();

		if (key.endsWith("2")) {
			jobDataMap.put("jobSays", "AAA:" + LocalTime.now());
		} else if (key.endsWith("3")) {
			jobDataMap.put("jobSays", "BBB:" + LocalTime.now());
		} else {
			jobDataMap.put("jobSays", "CCC:" + LocalTime.now());
		}

	}

	public String getJobSays() {
		return jobSays;
	}

	public void setJobSays(String jobSays) {
		this.jobSays = jobSays;
	}

	public Object getMyVal() {
		return myVal;
	}

	public void setMyVal(Object myVal) {
		this.myVal = myVal;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
}
