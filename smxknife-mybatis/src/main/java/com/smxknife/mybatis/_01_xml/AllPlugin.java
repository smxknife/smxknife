package com.smxknife.mybatis._01_xml;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author smxknife
 * 2021/6/23
 */
@Intercepts({
		// 这里的Signature定义的是拦截哪个类的哪个方法，比如这里拦截的是Executor类型，方法是query
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
public class AllPlugin implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		final Object proceed = invocation.proceed();
		System.out.println("All intercept | " + proceed.getClass());
		return proceed;
	}

	@Override
	public Object plugin(Object target) {

		System.out.println("All plugin target | " + target.getClass());
		final Object plugin = Interceptor.super.plugin(target);
		System.out.println("All plugin plugin | " + plugin.getClass());
		return plugin;
	}
}
