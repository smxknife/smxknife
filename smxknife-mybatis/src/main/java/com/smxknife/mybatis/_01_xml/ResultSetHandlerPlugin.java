package com.smxknife.mybatis._01_xml;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;

/**
 * @author smxknife
 * 2021/6/23
 */
@Intercepts({
		// 这里的Signature定义的是拦截哪个类的哪个方法，比如这里拦截的是Executor类型，方法是query
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class ResultSetHandlerPlugin implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		final Object proceed = invocation.proceed();
		if (proceed != null) {
			System.out.println("ResultSetHandler intercept | " + proceed.getClass());
		}
		return proceed;
	}

	@Override
	public Object plugin(Object target) {

		if (ResultSetHandler.class.isAssignableFrom(target.getClass())) {
			System.out.println("ResultSetHandler plugin target | " + target.getClass());
			final Object plugin = Interceptor.super.plugin(target);
			System.out.println("ResultSetHandler plugin plugin | " + plugin.getClass());
			return plugin;
		} else {
			return target;
		}
	}
}
