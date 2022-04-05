package com.smxknife.aviator.demo01;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/9/8
 */
public class HelloWorld {
	public static void main(String[] args) {
		try {
			Expression expression = AviatorEvaluator.getInstance().compileScript("demo01/hello.av");
			Object execute = expression.execute();
			System.out.println(execute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
