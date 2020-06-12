package com.smxknife.quartz.demo9;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/2/6
 */
public class MockJob implements Job {
	private static Map<String, AtomicInteger> numMap = new ConcurrentHashMap<>();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		numMap.putIfAbsent(jobDetail.getKey().getName(), new AtomicInteger(0));
		AtomicInteger atomicInteger = numMap.get(jobDetail.getKey().getName());
		int num = atomicInteger.incrementAndGet();
		System.out.println("Thread = " + Thread.currentThread().getName() + " , jobName = " + jobDetail.getKey().getName() + ", num = " + num);
	}
}
