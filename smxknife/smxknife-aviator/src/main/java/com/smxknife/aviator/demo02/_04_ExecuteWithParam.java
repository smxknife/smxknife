package com.smxknife.aviator.demo02;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _04_ExecuteWithParam {
	public static void main(String[] args) {
		String expression = "a-(b-c) > 100";
		Expression compiledExp = AviatorEvaluator.compile(expression);
		Boolean result =
				(Boolean) compiledExp.execute(compiledExp.newEnv("a", 100.3, "b", 45, "c", -199.100));
		System.out.println(result);
	}
}
