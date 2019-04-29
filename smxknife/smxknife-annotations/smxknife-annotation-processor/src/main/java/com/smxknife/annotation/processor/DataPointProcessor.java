package com.smxknife.annotation.processor;

import com.google.auto.service.AutoService;
import com.smxknife.annotation.processor.writer.DataPointWriter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author smxknife
 * 2019-03-28
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class DataPointProcessor extends AbstractProcessor {

	private Types types;
	private Elements elements;
	private Filer filer;
	private Messager messager;
	private DataPointGenerator dataPointGenerator;

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
		for (TypeElement annotation : annotations) {
			System.out.println(((TypeElement) annotation).getQualifiedName().toString());
			Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotation);

			if (elementsAnnotatedWith.size() == 0) {
				System.out.println("warning");
				return true;
			} else if (elementsAnnotatedWith.size() > 1) {
				error(null,
						"Only one class can be annotated with @%s in same classpath.",
						DataPointAdapter.class.getSimpleName());
				return true;
			}

			Element element = elementsAnnotatedWith.stream().findAny().get();
			if (element.getKind() != ElementKind.CLASS) {
				error(element, "Only class can be annotated with @%s", DataPointAdapter.class.getSimpleName());
				return true;
			}

			TypeElement typeElement = (TypeElement) element;
			DataPointAnnotatedClass annotatedClass = new DataPointAnnotatedClass(typeElement);

			checkValidClass(annotatedClass);

			dataPointGenerator = new CassandraDataPointGenerator(annotatedClass.getQualifiedAnnotatedClassName());
//			dataPointGenerator.generate(elements, filer);

			DataPointWriter dataPointWriter = new DataPointWriter();
			dataPointWriter.addAnnotation("L" + Deprecated.class.getCanonicalName().replaceAll("\\.", "/"));
		}

		} catch (ProcessingException e) {
			error(e.element, e.getMessage());
		} catch (Exception e) {
			error(null, e.getMessage());
		}

		return false;
	}

	private void checkValidClass(DataPointAnnotatedClass annotatedClass) throws ProcessingException {
		TypeElement typeElement = annotatedClass.getAnnotatedClassElement();
		if (!typeElement.getModifiers().contains(Modifier.PUBLIC)) {
			throw new ProcessingException(typeElement,
					"The class %s is not public.",
					typeElement.getQualifiedName().toString());
		}

		if (typeElement.getModifiers().contains(Modifier.ABSTRACT)) {
			throw new ProcessingException(typeElement,
					"The class %s is abstract.",
					typeElement.getQualifiedName().toString());
		}
	}

	private void error(Element element, String msg, Object ...args) {
		messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), element);
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<>();
		annotations.add(DataPointAdapter.class.getCanonicalName());
		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		types = processingEnv.getTypeUtils();
		elements = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
	}
}
