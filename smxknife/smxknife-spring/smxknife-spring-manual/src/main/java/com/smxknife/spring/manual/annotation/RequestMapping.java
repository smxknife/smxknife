package com.smxknife.spring.manual.annotation;

import java.lang.annotation.*;

/**
 * @author smxknife
 * 2019/12/30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
	String value() default "";
}
