package com.smxknife.energy.clouds.ents.entmgr.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/20
 */
@Component
public class TaskEngine implements CommandLineRunner {

	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	@Autowired
	private EntMinutesTask minutesTask;

	@Override
	public void run(String... args) throws Exception {
		executorService.scheduleAtFixedRate(minutesTask, 5, 5, TimeUnit.MINUTES);
	}
}
