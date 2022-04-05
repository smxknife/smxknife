package com.smxknife.aviator.demo02;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _02_CompileString_Cache {
	public static void main(String[] args) {
		// 通过compile方法可以编译字符串类型的脚本，说明可以将脚本存储在数据库或其他介质中
		System.out.println("no cached");
		for (int i = 0; i < 3; i++) {
			Expression expression = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
			System.out.println(expression);
		}
		System.out.println("cached");

		for (int i = 0; i < 3; i++) {
			Expression expression = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');", true);
			System.out.println(expression);
		}

	}
}
