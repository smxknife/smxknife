package com.smxknife.annotation.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author smxknife
 * 2019-03-28
 */
public class CassandraDataPointGenerator implements DataPointGenerator {

	private String qualifiedClassName;

	public CassandraDataPointGenerator(String qualifiedClassName) {
		this.qualifiedClassName = qualifiedClassName;
	}

	@Override
	public void generate(Elements elements, Filer filer) {
		TypeElement typeElement = elements.getTypeElement(this.qualifiedClassName);
		String path = qualifiedClassName.replaceAll("\\.", "/");
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		cw.visit(ASM4, Opcodes.ACC_PUBLIC, path, null, "java/lang/Object", null);
		{
			String annotation = "L" + Deprecated.class.getCanonicalName().replaceAll("\\.", "/");
			AnnotationVisitor av0 = cw.visitAnnotation(annotation, true);
			av0.visitEnd();
		}

		cw.visitEnd();


		byte[] bytes = cw.toByteArray();
		System.out.println("||||||||||");

		System.out.println(new String(bytes));

		ClassName className = ClassName.get(elements.getTypeElement(this.qualifiedClassName));
		System.out.println("=======" + className.toString());
		TypeSpec typeSpec = TypeSpec.classBuilder(className)
				.addAnnotation(Deprecated.class).build();

		JavaFile javaFile = JavaFile.builder(qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf(".")), typeSpec).build();
		try {
			javaFile.writeTo(filer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
