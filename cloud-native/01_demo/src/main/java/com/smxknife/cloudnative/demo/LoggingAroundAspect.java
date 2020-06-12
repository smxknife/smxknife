package com.smxknife.cloudnative.demo;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/5/23
 */
@Component
@Aspect
public class LoggingAroundAspect {
	private Log log = LogFactory.getLog(getClass());

	@Around("execution(* com.smxknife.cloudnative.demo.CustomerService.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		LocalDateTime start = LocalDateTime.now();

		Throwable throwable = null;
		Object returnValue = null;

		try {
			returnValue = joinPoint.proceed();
		} catch (Throwable th) {
			throwable = th;
		}
		LocalDateTime stop = LocalDateTime.now();

		log.info("starting @ " + start.toString());
		log.info("finishing @ " + stop.toString() + " with duration " + stop.minusNanos(start.getNano()).getNano());

		if (null != throwable) {
			throw throwable;
		}
		return returnValue;
	}
}
