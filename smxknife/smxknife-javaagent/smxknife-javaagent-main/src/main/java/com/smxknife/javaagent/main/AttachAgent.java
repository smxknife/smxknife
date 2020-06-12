package com.smxknife.javaagent.main;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/6/10
 */
public class AttachAgent {
	public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
		VirtualMachine machine = VirtualMachine.attach("47180");
		machine.loadAgent("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-javaagent/smxknife-javaagent-agent/target/smxknife-javaagent-agent-1.0-SNAPSHOT-jar-with-dependencies.jar=HHHH");
	}
}
