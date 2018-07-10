package com.smxknife.springboot.v1.controlleraop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {
  @Pointcut("execution(public * com.smxknife.springboot.v1.controlleraop.controller.HelloController.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) {
    System.out.println("hello");
  }
}
