package com.smxknife.spring.ignoredependency.type;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author smxknife
 * 2020/2/5
 */
public class TypeTest {

	@Setter
	private IgnoreType ignoreType;

	public void test() {
		System.out.println("test() ignoreType : " + ignoreType);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("ignoreType : " + ignoreType);
	}
}
