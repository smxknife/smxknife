package com.smxknife.asm.demo02;

import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author smxknife
 * 2018-12-18
 */
public class Main {
	public static void main(String[] args) throws IOException {
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
//		System.out.println(classWriter.toString());

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		byte[] tmp = new byte[bytes.length];
		dis.read(tmp);
		System.out.println(new String(tmp));

		MyClassLoader myClassLoader = new MyClassLoader();
		Class aClass = myClassLoader.defineClass("com.smxknife.asm.demo02.Comparable", bytes);
		System.out.println(aClass);
	}
}
