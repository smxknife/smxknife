package com.smxknife.drools.demo02.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import java.util.Date;
import java.util.UUID;

/**
 * @author smxknife
 * 2020/6/15
 */
public class UUIDUtil {
	/**
	 * 生成原始UUID
	 */
	private static String uuidString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	/**
	 * 生成格式化UUID
	 * @param replace
	 * @return
	 */
	public static String uuidFormatString(String replace) {
		String uuid = UUID.randomUUID().toString().replaceAll(replace, "");
		return uuid;
	}

	/**
	 * 值加密
	 * @return
	 */
	public static String md5AndUUID() {
		String timeJab = String.valueOf(System.currentTimeMillis());
		// UUID + 时间戳
		String concat = uuidString().concat(timeJab);
		return DigestUtils.md5Hex(concat);
	}

	/**
	 * 生成不重复的促销编码
	 * @return
	 */
	public static String typeJoinTime() {
		String dateNowStr = StringJoinUtil.dateToStringThree(new Date());
		Integer math = (int) ((Math.random() * 9 + 1) * 1_000_000);
		String code = dateNowStr.concat(math.toString());
		return code;
	}

	public static String rule(String json) {
		String rule = ruleWordExchangesST(json);
		return rule;
	}

	private static String ruleWordExchangesST(String json) {
		STGroup group = new STGroupString(LossMoneyTemplate.workMoneyST);
		System.out.println("=== group\r\n" + group);
		System.out.println("-----");
		System.out.println("=== temp\r\n" + LossMoneyTemplate.workMoneyST);
		System.out.println("-----");
		ST stFile = group.getInstanceOf("wordImport");
		ST stRule = group.getInstanceOf("ruleValue");
		JSONObject jsonObject = JSONObject.parseObject(json);
		JSONObject condition = jsonObject.getJSONObject("condition");
		JSONObject action = jsonObject.getJSONObject("action");
		JSONObject rule = jsonObject.getJSONObject("rule");
		stRule.add("condition", condition);
		stRule.add("action", action);
		stRule.add("rule", rule);
		stFile.add("rules", stRule);
		String render = stFile.render();
		return render;
	}
}
