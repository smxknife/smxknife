package com.smxknife.aviator.demo04;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _04_Type {
	public static void main(String[] args) throws IOException {
		Expression expression = AviatorEvaluator.getInstance().compileScript("demo04/_04_type.av");
		expression.execute();
	}
}
