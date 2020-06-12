package com.smxknife.patterns.dispatcher.demo1;

/**
 * @author smxknife
 * 2019/12/28
 */
public class EmployeeB implements IEmployee {
	@Override
	public void doing(String command) {
		System.out.println("B work, do " + command);
	}
}
