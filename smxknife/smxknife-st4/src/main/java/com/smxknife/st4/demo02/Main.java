package com.smxknife.st4.demo02;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.LinkedList;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/18
 */
public class Main {
	public static void main(String[] args) {
//		InputStream inputStream = com.smxknife.st4.demo01.Main.class.getClassLoader().getResourceAsStream("demo02/rule.temp");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//		String temp = reader.lines().collect(Collectors.joining("\r\n"));
//		System.out.println(temp);

		STGroup stg = new STGroupFile("demo02/rule.stg");
		ST sqlST = stg.getInstanceOf("sqlTemplate");

		List<String> columnList = new LinkedList<String>();
		columnList.add("order_id");
		columnList.add("price");
		columnList.add("phone");
		columnList.add("user");

		sqlST.add("columns", columnList);
		sqlST.add("condition", "dt='2017-04-04'");
		sqlST.add("joinKey", "user");
		sqlST.add("tableName", "orderTable");

		List<String> childColumnList = new LinkedList<String>();
		childColumnList.add("user");
		childColumnList.add("userLeave");
		childColumnList.add("userLocation");
		sqlST.add("childColumns", childColumnList);
		sqlST.add("childJoinKey", "user");
		sqlST.add("childTableName", "userTable");

		String result = sqlST.render();

		System.out.print(result);
	}
}
