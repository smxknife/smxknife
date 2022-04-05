package com.smxknife.flowable.demo2._ex1;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/11/17
 */
public class _TaskHandler {
	public static void main(String[] args) {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/flowable")
				.setJdbcDriver("com.mysql.cj.jdbc.Driver")
				.setJdbcUsername("root")
				.setJdbcPassword("root")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// Step1: 创建流程引擎
		ProcessEngine processEngine = cfg.buildProcessEngine();

		// Step2: 创建TaskService
		TaskService taskService = processEngine.getTaskService();

		// Step3: 创建TaskQuery
		TaskQuery taskQuery = taskService.createTaskQuery();

		// 可以加候选组的条件
		// taskQuery.taskCandidateGroup("manager").list();

		// 这里没加任何条件，列出所有的左右
		// Step4: TaskService完成工作
		List<Task> tasks = taskQuery.list();
		tasks.forEach(task -> {
			System.out.println("-------------------------------");
			System.out.println("task:getAssignee | " + task.getAssignee());
			System.out.println("task:getCategory | " + task.getCategory());
			System.out.println("task:getDescription | " + task.getDescription());
			System.out.println("task:getExecutionId | " + task.getExecutionId());
			System.out.println("task:getFormKey | " + task.getFormKey());
			System.out.println("task:getId | " + task.getId());
			System.out.println("task:getName | " + task.getName());
			System.out.println("task:getOwner | " + task.getOwner());
			System.out.println("task:getParentTaskId | " + task.getParentTaskId());
			System.out.println("task:getProcessDefinitionId | " + task.getProcessDefinitionId());
			System.out.println("task:getProcessInstanceId | " + task.getProcessInstanceId());
			System.out.println("task:getPropagatedStageInstanceId | " + task.getPropagatedStageInstanceId());
			System.out.println("task:getScopeDefinitionId | " + task.getScopeDefinitionId());
			System.out.println("task:getScopeId | " + task.getScopeId());
			System.out.println("task:getScopeType | " + task.getScopeType());
			System.out.println("task:getSubScopeId | " + task.getSubScopeId());
			System.out.println("task:getTaskDefinitionId | " + task.getTaskDefinitionId());
			System.out.println("task:getProcessVariables | " + task.getProcessVariables());
			System.out.println("task:getTaskLocalVariables | " + task.getTaskLocalVariables());

			Map<String, Object> variables = taskService.getVariables(task.getId());
			System.out.println("variables | " + variables);

			// 通过调用complete方法完成工作
			Map<String, Object> completeVariables = new HashMap<>();
			completeVariables.put("approved", true);
			try {
				taskService.complete(task.getId(), completeVariables);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}
}
