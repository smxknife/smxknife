package com.smxknife.aviator.demo03;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _05_Trans {
	public static void main(String[] args) throws IOException {
		Expression expression = AviatorEvaluator.getInstance().compileScript("demo03/_05_trans.av");
		expression.execute();
	}
}
