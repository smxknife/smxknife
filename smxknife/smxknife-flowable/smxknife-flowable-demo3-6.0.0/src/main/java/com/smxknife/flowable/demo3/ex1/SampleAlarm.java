package com.smxknife.flowable.demo3.ex1;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2020/12/21
 */
public class SampleAlarm {
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
		// Step2: 部署流程定义
		// 定义中的每一个步骤称为一个活动（Activity），都有一个id属性。所有的活动都可以提供一个名字，提高可读性
		// 活动之间通过顺序流（Sequence Flow）连接
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// repositoryService用于部署定义
		//Deployment deployment = repositoryService.createDeployment().addClasspathResource("holiday-request.bpmn").deploy();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("ex1/简单告警流程.bpmn20.xml").deploy();

		// Step3: 启动流程实例RuntimeService
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<>();
		variables.put("employee", "hello");
		variables.put("nrOfHolidays", 10);
		variables.put("description", true);

		runtimeService.addEventListener(new AlarmTaskListener());

		runtimeService.startProcessInstanceByKey("alarmProcess", variables);

	}
}
