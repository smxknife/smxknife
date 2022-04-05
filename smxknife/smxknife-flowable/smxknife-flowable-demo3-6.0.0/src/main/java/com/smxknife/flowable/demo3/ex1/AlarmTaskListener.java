package com.smxknife.flowable.demo3.ex1;


import org.flowable.engine.common.api.delegate.event.FlowableEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.engine.common.api.delegate.event.FlowableEventType;

/**
 * @author smxknife
 * 2020/12/21
 */
public class AlarmTaskListener implements FlowableEventListener {

	@Override
	public void onEvent(FlowableEvent flowableEvent) {
		FlowableEventType type = flowableEvent.getType();
		System.out.println("----------- " + type);
	}

	@Override
	public boolean isFailOnException() {
		return false;
	}
}
