package com.smxknife.patterns.dispatcher.demo1;

/**
 * @author smxknife
 * 2019/12/28
 */
public class EmployeeA implements IEmployee {
	@Override
	public void doing(String command) {
		System.out.println("A work, do " + command);
	}
}
