package com.smxknife.asm.demo01;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author smxknife
 * 2018-12-18
 */
public class ClassPrinter extends ClassVisitor {

	public ClassPrinter() {
		super(ASM4);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name + " extends " + superName + " {");
	}

	@Override
	public void visitSource(String source, String debug) {
	}

	@Override
	public void visitOuterClass(String owner, String name, String descriptor) {
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
		return null;
	}

	@Override
	public void visitAttribute(Attribute attribute) {
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
	}

	@Override
	public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
		System.out.println(" " + descriptor + " " + name);
		return null;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
		System.out.println(" " + name + descriptor);
		return null;
	}

	@Override
	public void visitEnd() {
		System.out.println("}");
	}
}
