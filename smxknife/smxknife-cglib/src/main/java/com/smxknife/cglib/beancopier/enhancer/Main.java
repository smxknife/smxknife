package com.smxknife.cglib.beancopier.enhancer;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author smxknife
 * 2019/12/27
 */
public class Main {
	public static void main(String[] args) {

		// 为了可以清楚的看到cglib的原理，将cglib生成的类写入磁盘
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib_proxy.class");

		Subject instance = (Subject) new SubjectProxy().getInstance(Subject.class);
		instance.dodo();
	}
}
