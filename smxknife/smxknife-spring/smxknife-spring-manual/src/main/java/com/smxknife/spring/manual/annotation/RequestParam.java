package com.smxknife.spring.manual.annotation;

import java.lang.annotation.*;

/**
 * @author smxknife
 * 2019/12/30
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
	String value() default "";
}
