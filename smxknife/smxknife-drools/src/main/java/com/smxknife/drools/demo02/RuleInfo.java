package com.smxknife.drools.demo02;

/**
 * @author smxknife
 * 2020/6/10
 */
public class RuleInfo {

	/**
	 * 规则id，全局唯一
	 */
	private Long id;

	/**
	 * 场景id，一个场景对应多个规则，一个场景对应一个业务场景，一个场景对应一个kmodule
	 */
	private Long sceneId;

	/**
	 * 规则内容，即drl文件内容
	 */
	private String content;

}
