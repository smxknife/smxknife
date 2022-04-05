package com.smxknife.flowable.demo;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class ProcessEngineUtil {

	public static ProcessEngine build() {
		ProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://localhost:3306/flowable")
				.setJdbcDriver("com.mysql.cj.jdbc.Driver")
				.setJdbcUsername("root")
				.setJdbcPassword("root")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		return config.buildProcessEngine();
	}


}
