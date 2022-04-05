package com.smxknife.aviator.demo02;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _03_CompileString_Cache_Key {
	public static void main(String[] args) {
		// 通过compile方法可以编译字符串类型的脚本，说明可以将脚本存储在数据库或其他介质中
		System.out.println("cached");
		AviatorEvaluatorInstance evaluatorInstance = AviatorEvaluator.getInstance();
		for (int i = 0; i < 3; i++) {
			Expression expression = evaluatorInstance.compile("key" + i, "println('Hello, AviatorScript!" + i + "');", true);
			System.out.println(expression);
			expression.execute();
		}



	}
}
