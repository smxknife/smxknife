package com.smxknife.st4.demo03;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/19
 */
public class Main {
	public static void main(String[] args) {
		STGroupFile stGroupFile = new STGroupFile("demo03/rule.stg");
		ST contextTemp = stGroupFile.getInstanceOf("contextTemp");
		contextTemp.add("package", "com.stRule");

		List<String> imports = Arrays.asList(String.class.getCanonicalName(), Integer.class.getCanonicalName(), Rule.class.getCanonicalName());
		contextTemp.add("imports", imports);

		List<Rule> rules = Arrays.asList(new Rule("rule1", "condition1", "action1"), new Rule("rule2", "condition2", "action2"));
		contextTemp.add("rules", rules);
		System.out.println(contextTemp.render());

	}
}
