package com.smxknife.asm.demo02;

import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author smxknife
 * 2018-12-19
 */
public class SubClassLoader extends ClassLoader {
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		if (name.endsWith("_Stub")) {
			ClassWriter classWriter = new ClassWriter(0);
			classWriter.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
					"com/smxknife/asm/demo02/Comparable",
					null,
					"java/lang/Object",
					new String[] {"com/smxknife/asm/demo02/Mesurable"});
			classWriter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
					"less",
					"I",
					null,
					new Integer(-1)).visitEnd();
			classWriter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
					"equal",
					"I",
					null,
					new Integer(0)).visitEnd();
			classWriter.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
					"gather",
					"I",
					null,
					new Integer(1)).visitEnd();
			classWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT,
					"compareTo",
					"(Ljava/lang/Object;)I",
					null,
					null).visitEnd();
			classWriter.visitEnd();
			byte[] bytes = classWriter.toByteArray();
			return defineClass(name, bytes, 0, bytes.length);
		}
		return super.findClass(name);
	}
}
