package com.smxknife.spring.ignoredependency.inter;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author smxknife
 * 2020/2/5
 */
public class InterfaceTest {

	@Setter
	private IgnoreInterface ignoreInterface;

	public void test() {
		System.out.println("test() ignoreInterface" + ignoreInterface);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("ignoreInterface" + ignoreInterface);
	}
}
