package com.smxknife.javaagent.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author smxknife
 * 2020/6/10
 */
public class Agent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("PreAgent premain running");
		System.out.println(agentArgs);
	}

	public static void agentmain(String agentArgs, Instrumentation inst) {
		System.out.println("PreAgent agentmain running");
		System.out.println(agentArgs);
	}
}
