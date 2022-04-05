package com.smxknife.flowable.demo;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HolidayRequest {
	public static void main(String[] args) {

		ProcessEngine processEngine = ProcessEngineUtil.build();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("holiday-request.bpmn20.xml")
				.deploy();

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		System.out.printf("找到流程定义：name=%s, id=%s\n", processDefinition.getName(), processDefinition.getId());

		Scanner scanner= new Scanner(System.in);

		System.out.println("Who are you?");
		String employee = scanner.nextLine();

		System.out.println("How many holidays do you want to request?");
		Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

		System.out.println("Why do you need them?");
		String description = scanner.nextLine();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employee", employee);
		variables.put("nrOfHolidays", nrOfHolidays);
		variables.put("description", description);

		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
		System.out.println(processInstance.getId());
	}
}
