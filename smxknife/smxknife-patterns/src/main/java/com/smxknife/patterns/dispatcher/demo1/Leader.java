package com.smxknife.patterns.dispatcher.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/12/28
 */
public class Leader implements IEmployee {

	private Map<String, IEmployee> targets = new HashMap<>();

	public Leader() {
		targets.put("加密", new EmployeeA());
		targets.put("登录", new EmployeeB());
	}

	@Override
	public void doing(String command) {
		targets.get(command).doing(command);
	}
}
