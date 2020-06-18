package com.smxknife.drools.demo02.service;

import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;

/**
 * @author smxknife
 * 2020/6/15
 */
public class NewKieBase {
	public static KieBase ruleKieBase(String content) {
		KieHelper helper = new KieHelper();
		try {
			helper.addContent(content, ResourceType.DRL);
			return helper.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("规则初始化失败");
		}
	}
}
