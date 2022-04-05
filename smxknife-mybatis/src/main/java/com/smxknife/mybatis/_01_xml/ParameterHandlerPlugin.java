package com.smxknife.mybatis._01_xml;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.PreparedStatement;

/**
 * @author smxknife
 * 2021/6/23
 */
@Intercepts({
		// 这里的Signature定义的是拦截哪个类的哪个方法，比如这里拦截的是Executor类型，方法是query
		@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
})
public class ParameterHandlerPlugin implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		final Object proceed = invocation.proceed();
		if (proceed != null) {
			System.out.println("ParameterHandlerPlugin intercept | " + proceed.getClass());
		}
		return proceed;
	}

	@Override
	public Object plugin(Object target) {

		if (ParameterHandler.class.isAssignableFrom(target.getClass())) {
			System.out.println("ParameterHandler plugin target | " + target.getClass());
			final Object plugin = Interceptor.super.plugin(target);
			System.out.println("ParameterHandler plugin plugin | " + plugin.getClass());
			return plugin;
		} else {
			return target;
		}
	}
}
