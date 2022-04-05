package com.smxknife.aviator.demo01;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/9/8
 */
public class HelloWorld2 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 5; i++) {
				Expression expression = AviatorEvaluator.getInstance().compileScript("demo01/hello.av");
				System.out.println(expression); // 这里面每次循环输出不一样，说明每次都会产生一个新的对象，所以如果使用不当会导致频繁full gc或者OOM
				Object execute = expression.execute();
				System.out.println(execute);
				System.out.println("=====================");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
