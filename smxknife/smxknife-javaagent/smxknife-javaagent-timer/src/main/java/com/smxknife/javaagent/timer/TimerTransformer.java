package com.smxknife.javaagent.timer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author smxknife
 * 2020/6/10
 */
public class TimerTransformer implements ClassFileTransformer {
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

		// 只针对目标包进行统计
		if (!className.startsWith("com/smxknife/javaagent/app")) {
			return classfileBuffer;
		}



		return new byte[0];
	}
}
