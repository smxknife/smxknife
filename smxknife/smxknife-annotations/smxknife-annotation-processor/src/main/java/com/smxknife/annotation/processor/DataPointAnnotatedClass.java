package com.smxknife.annotation.processor;

import lombok.Getter;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import java.util.Objects;

/**
 * @author smxknife
 * 2019-03-28
 */
@Getter
public class DataPointAnnotatedClass {

	private TypeElement annotatedClassElement;
	private String qualifiedAnnotatedClassName;
	private String simpleAnnotatedClassName;
	private String id;
	private String name;

	public DataPointAnnotatedClass(TypeElement annotatedClassElement) {
		this.annotatedClassElement = annotatedClassElement;
		DataPointAdapter annotation = annotatedClassElement.getAnnotation(DataPointAdapter.class);
		id = annotation.id();
		if (Objects.isNull(id) || "".equals(id))
			throw new IllegalArgumentException("id is null or empty");

		name = annotation.name();

		try {
			qualifiedAnnotatedClassName = DataPoint.class.getCanonicalName();
			simpleAnnotatedClassName = DataPoint.class.getSimpleName();
		} catch (MirroredTypeException mte) {
			DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
			TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
			qualifiedAnnotatedClassName = classTypeElement.getQualifiedName().toString();
			simpleAnnotatedClassName = classTypeElement.getSimpleName().toString();
		}
	}
}
