package com.smxknife.energy.collector.core.driver.datasource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2021/5/12
 */
@Getter
@Setter
public class Datasource {

	private String uid;
	private String name;
	private String host;
	private Integer port;
	private String account;
	private String password;
	private String cron;
	private String type;
}
