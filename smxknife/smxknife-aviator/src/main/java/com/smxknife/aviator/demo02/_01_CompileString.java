package com.smxknife.aviator.demo02;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _01_CompileString {
	public static void main(String[] args) {
		// 通过compile方法可以编译字符串类型的脚本，说明可以将脚本存储在数据库或其他介质中
		Expression expression = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
		expression.execute();
	}
}
