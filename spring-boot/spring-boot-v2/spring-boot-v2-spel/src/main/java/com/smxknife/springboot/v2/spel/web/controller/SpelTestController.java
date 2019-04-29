package com.smxknife.springboot.v2.spel.web.controller;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author smxknife
 * 2019-03-01
 */
@RestController
public class SpelTestController {

	@RequestMapping
	public String test() {
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext ec = new StandardEvaluationContext();
//		Expression expression = parser.parseExpression("(1+ (2-3) + 5) * 4 + pow(46,2) * avg(1,2,3,4) + pow(avg(3,4,5,6),3)");

//		Expression expression = parser.parseExpression("2 * ((1+ (2-3) + 5) * 4)");
//
//		System.out.println(expression);
//		System.out.println(expression.getExpressionString());
//		System.out.println(expression.getValue());

//		Expression e2 = parser.parseExpression("(1+2) sqrt 2");
//		System.out.println(e2.getValue(ec));

		Object user = new Object() {
			public String getUser(String code) {
				if ("admin".equals(code)) return "admin";
				else return "other";
			}

			public int list(List<String> users) {
				users.forEach(System.out::println);
				return users.size();
			}
		};
		EvaluationContext context = new StandardEvaluationContext(user);
		String user1 = parser.parseExpression("getUser('admin')").getValue(context, String.class);
		System.out.println(user1);

		Object value = parser.parseExpression("1 + list({'aaa','bbb','dddd'})").getValue(context);
		System.out.println(value);

		return "";
	}
}
