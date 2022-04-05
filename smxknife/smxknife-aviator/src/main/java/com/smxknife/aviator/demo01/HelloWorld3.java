package com.smxknife.aviator.demo01;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/9/8
 */
public class HelloWorld3 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 5; i++) {
				Expression expression = AviatorEvaluator.getInstance()
						.compileScript("demo01/hello.av", true);
				System.out.println(expression); // 加上缓存之后会缓存第一次加载的文件生成的对象，所以每个对象都是同一个，但是后面如果hello.av做过修改，也是无法获取修改的内容
				Object execute = expression.execute();
				System.out.println(execute);
				System.out.println("=====================");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
