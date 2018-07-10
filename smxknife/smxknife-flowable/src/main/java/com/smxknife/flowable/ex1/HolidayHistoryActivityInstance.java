package com.smxknife.flowable.ex1;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

import java.util.List;

public class HolidayHistoryActivityInstance {
	public static void main(String[] args) {
		ProcessEngine processEngine = ProcessEngineUtil.build();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("holiday-request.bpmn20.xml")
				.deploy();

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		System.out.printf("找到流程定义：name=%s, id=%s\n", processDefinition.getName(), processDefinition.getId());

		HistoryService historyService = processEngine.getHistoryService();
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId("30004")
				.finished()
				.orderByHistoricActivityInstanceEndTime().asc()
				.list();

		for (HistoricActivityInstance inst : list) {
			System.out.println(inst.getActivityId() + " took "
					+ inst.getDurationInMillis() + " milliseconds");
		}
	}
}
