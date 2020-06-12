package com.smxknife.drools.demo01;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author smxknife
 * 2020/6/10
 */
public class Main {
	public static void main(String[] args) {
		// 构建KieServices
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		// 获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
		KieSession kSession = kc.newKieSession("ksession1");

		Product fan = new Product("电扇", 280);
		Product washer = new Product("洗衣机",2200);
		Product phone = new Product("手机", 998);
		kSession.insert(fan);
		kSession.insert(washer);
		kSession.insert(phone);
		kSession.fireAllRules();
		kSession.dispose();
	}
}
