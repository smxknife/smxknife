package com.smxknife.annotation.processor.writer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author smxknife
 * 2019-03-28
 */
public class DataPointWriter {

	ClassReader reader;
	ClassWriter writer;
	AddAnnotationAdapter addAnnotationAdapter;
	final static String CLASSNAME = "com.smxknife.annotation.example.DataPoint";
	final static String CLONEABLE = "com/smxknife/annotation/example/DataPoint";

	public DataPointWriter() {
		try {
			reader = new ClassReader(CLASSNAME);
			writer = new ClassWriter(reader, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataPointWriter(byte[] content) {
		reader = new ClassReader(content);
		writer = new ClassWriter(reader, 0);
	}

	public byte[] addAnnotation(String ann) {
		addAnnotationAdapter = new AddAnnotationAdapter(ann, writer);
		reader.accept(addAnnotationAdapter, 0);
		return writer.toByteArray();
	}

	public class AddAnnotationAdapter extends ClassVisitor {

		private String annotation;
		private boolean isPresent;

		public AddAnnotationAdapter(String annotation, ClassVisitor cv) {
			super(ASM4, cv);
			this.cv = cv;
			this.annotation = annotation;
		}

		@Override
		public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
			if (annotation.equals(descriptor)) isPresent = true;
			return cv.visitAnnotation(descriptor, visible);
		}

		@Override
		public void visitEnd() {
			if (!isPresent) {
				AnnotationVisitor av = cv.visitAnnotation(annotation, true);
				av.visitEnd();
			}
			cv.visitEnd();
		}
	}

}
