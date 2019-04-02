package com.smxknife.annotation.processor;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

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

		System.out.println("=========+++++++");

		StringBuilder builder = new StringBuilder()
				.append("package " + qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("\\.")) + ";\r\n")
				.append("public class ")
				.append(typeElement.getSimpleName().toString() + "A")
				.append(" {}\r\n");

		try {
			JavaFileObject classFile = filer.createClassFile(qualifiedClassName);
			Writer writer = classFile.openWriter();
			writer.write(builder.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
