package com.smxknife.flowable.demo2._ex1;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2020/11/17
 */
public class _HolidayRequest {
	public static void main(String[] args) {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/flowable")
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
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("请假流程.bpmn20.xml").deploy();
		System.out.println("deployment:id | " + deployment.getId());
		System.out.println("deployment:category | " + deployment.getCategory());
		System.out.println("deployment:getDerivedFrom | " + deployment.getDerivedFrom());
		System.out.println("deployment:getDerivedFromRoot | " + deployment.getDerivedFromRoot());
		System.out.println("deployment:getEngineVersion | " + deployment.getEngineVersion());
		System.out.println("deployment:getKey | " + deployment.getKey());
		System.out.println("deployment:getName | " + deployment.getName());
		System.out.println("deployment:getTenantId | " + deployment.getTenantId());
		System.out.println("deployment:getDeploymentTime | " + deployment.getDeploymentTime());

		// 可以通过RepositoryService查询已部署在引擎中的定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		System.out.println("ProcessDefinition = " + processDefinition.getName());
		System.out.println("processDefinition:id | " + processDefinition.getId());
		System.out.println("processDefinition:getCategory | " + processDefinition.getCategory());
		System.out.println("processDefinition:getDeploymentId | " + processDefinition.getDeploymentId());
		System.out.println("processDefinition:getDerivedFrom | " + processDefinition.getDerivedFrom());
		System.out.println("processDefinition:getDerivedFromRoot | " + processDefinition.getDerivedFromRoot());
		System.out.println("processDefinition:getDescription | " + processDefinition.getDescription());
		System.out.println("processDefinition:getDiagramResourceName | " + processDefinition.getDiagramResourceName());
		System.out.println("processDefinition:getEngineVersion | " + processDefinition.getEngineVersion());
		System.out.println("processDefinition:getResourceName | " + processDefinition.getResourceName());
		System.out.println("processDefinition:getDerivedVersion | " + processDefinition.getDerivedVersion());
		System.out.println("processDefinition:getVersion | " + processDefinition.getVersion());

		// Step3: 启动流程实例RuntimeService
		RuntimeService runtimeService = processEngine.getRuntimeService();
		Map<String, Object> variables = new HashMap<>();
		variables.put("employee", "hello");
		variables.put("nrOfHolidays", 10);
		variables.put("description", true);
		runtimeService.startProcessInstanceByKey("holidayRequest", variables);
		// 这里要注意两个地方，1. holidayRequest是process的id属性；2. startProcessInstanceByKey而不是startProcessInstanceById
		// startProcessInstanceById，这里的id对应的是引擎内部的id（带版本的）


	}
}
