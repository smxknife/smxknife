package com.smxknife.st4.demo04;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2020/12/10
 */
public class Main {
	public static void main(String[] args) {
		STGroupFile st = new STGroupFile("demo04/pom.stg", '$', '$');
		ST context = st.getInstanceOf("pomContext");
		Map<String, String> parent = new HashMap<>();
		parent.put("groupId", "com.test");

		context.add("parent", parent);

		Map<String, String> module = new HashMap<>();
		module.put("artifi", "");
		module.put("groupId", "com.test2");
		context.add("module", module);
		System.out.println(context.render());
	}
}
