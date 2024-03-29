package com.smxknife.flowable.demo2._ex1;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CallExternalSystemDelegate implements JavaDelegate {
	@Override
	public void execute(DelegateExecution delegateExecution) {
		System.out.println("Calling the external system for employee "
				+ delegateExecution.getVariable("employee"));
	}
}
