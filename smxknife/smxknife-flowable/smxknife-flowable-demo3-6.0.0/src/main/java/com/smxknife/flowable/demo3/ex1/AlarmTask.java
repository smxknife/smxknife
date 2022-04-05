package com.smxknife.flowable.demo3.ex1;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/12/21
 */
public class AlarmTask {
	public static void main(String[] args) {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/iip")
				.setJdbcDriver("com.mysql.cj.jdbc.Driver")
				.setJdbcUsername("root")
				.setJdbcPassword("root")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// 线程安全的，可以全局唯一
		// Step1: 创建流程引擎
		ProcessEngine processEngine = cfg.buildProcessEngine();

		TaskService taskService = processEngine.getTaskService();

		List<Task> list = taskService.createTaskQuery().list();
		list.forEach(task -> {
			System.out.println("|||||||||||||||||" + task.getName());
			Map<String, Object> param = new HashMap<>();
			param.put("isClose", false);
			param.put("isOpen", false);
			param.put("needReport", false);
			taskService.complete(task.getId(), param);
		});


	}
}
