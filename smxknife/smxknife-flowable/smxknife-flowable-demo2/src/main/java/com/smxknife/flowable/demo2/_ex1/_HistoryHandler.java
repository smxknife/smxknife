package com.smxknife.flowable.demo2._ex1;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * @author smxknife
 * 2020/11/18
 */
public class _HistoryHandler {
	public static void main(String[] args) {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/flowable")
				.setJdbcDriver("com.mysql.cj.jdbc.Driver")
				.setJdbcUsername("root")
				.setJdbcPassword("root")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// Step1: 创建流程引擎
		ProcessEngine processEngine = cfg.buildProcessEngine();

		// Step2: 创建历史服务
		HistoryService historyService = processEngine.getHistoryService();
//		historyService.createHistoricActivityInstanceQuery()
//				.processInstanceId()
	}
}
