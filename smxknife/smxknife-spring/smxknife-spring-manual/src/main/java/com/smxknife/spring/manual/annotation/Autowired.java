package com.smxknife.spring.manual.annotation;

import java.lang.annotation.*;

/**
 * @author smxknife
 * 2019/12/30
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
	String value() default "";
}
