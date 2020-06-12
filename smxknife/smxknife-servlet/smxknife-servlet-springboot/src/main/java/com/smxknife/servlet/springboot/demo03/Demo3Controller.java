package com.smxknife.servlet.springboot.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2020/2/6
 */
@RestController
public class Demo3Controller {

	private IgnoreInterface ignoreInterface;

	private IgnoreType ignoreType;

	@Autowired
	public void setIgnoreInterface(IgnoreInterface ignoreInterface) {
		this.ignoreInterface = ignoreInterface;
	}

	@Autowired
	public void setIgnoreType(IgnoreType ignoreType) {
		this.ignoreType = ignoreType;
	}

	public IgnoreInterface getIgnoreInterface() {
		return this.ignoreInterface;
	}

	public IgnoreType getIgnoreType() {
		return this.ignoreType;
	}

//	public Demo3Controller(IgnoreInterface ignore, IgnoreType ignoreType) {
//		this.ignoreInterface = ignore;
//		this.ignoreType = ignoreType;
//	}

	@RequestMapping("")
	public void post() {
		System.out.println(ignoreInterface);
		System.out.println(ignoreType);
	}
}
