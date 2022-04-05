package com.smxknife.flowable.demo;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HolidayManager {
	public static void main(String[] args) {

		ProcessEngine processEngine = ProcessEngineUtil.build();

		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
		System.out.println("You have " + tasks.size() + " tasks:");
		for (int i=0; i<tasks.size(); i++) {
			System.out.println((i+1) + ") " + tasks.get(i).getName());
		}

		System.out.println("Which task would you like to complete?");
		Scanner scanner= new Scanner(System.in);
		int taskIndex = Integer.valueOf(scanner.nextLine());
		Task task = tasks.get(taskIndex - 1);
		Map<String, Object> processVariables = taskService.getVariables(task.getId());
		System.out.println(processVariables.get("employee") + " wants " + processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

		boolean approved = scanner.nextLine().toLowerCase().equals("y");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", approved);
		taskService.complete(task.getId(), variables);
	}
}
