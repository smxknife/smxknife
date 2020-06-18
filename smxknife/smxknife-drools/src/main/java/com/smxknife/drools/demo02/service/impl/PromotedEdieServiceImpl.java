package com.smxknife.drools.demo02.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smxknife.drools.demo02.dao.PromoteExecuteMapper;
import com.smxknife.drools.demo02.model.PromoteExecute;
import com.smxknife.drools.demo02.model.RuleResult;
import com.smxknife.drools.demo02.service.DrlExecute;
import com.smxknife.drools.demo02.service.PromoteEdieService;
import com.smxknife.drools.demo02.service.PromoteNeaten;
import com.smxknife.drools.demo02.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/6/15
 */
@Service
public class PromotedEdieServiceImpl implements PromoteEdieService {

	@Autowired
	private PromoteExecuteMapper promoteExecuteMapper;

	@Autowired
	private PromoteNeaten promoteNeaten;

	private Map<String, PromoteExecute> promoteExecuteMap;


	/**
	 * 生成优惠券
	 * @param money
	 * @param ruleName
	 */
	@Transactional
	@Override
	public void ediePromoteMap(String money, String ruleName) {
		if (this.promoteExecuteMap == null) {
			promoteExecuteMap = new HashMap<>();
		}

		PromoteExecute promoteExecute = new PromoteExecute();
		double v = Double.parseDouble(money);
		String rule = UUIDUtil.rule(ruleWorkMap(ruleName, v));
		System.out.println("=== rule\r\n" + rule);
		String promoteCode = UUIDUtil.typeJoinTime();
		promoteExecute.setPromoteCode(promoteCode);
		promoteExecute.setWorkContent(rule);
		promoteExecute.setPromoteName(ruleName);
		// 插入优惠券
		int i = promoteExecuteMapper.insertPromoteExecute(promoteExecute);
		if (i > 0) {
			PromoteExecute execute = promoteNeaten.editRule(rule);
			this.promoteExecuteMap.put(promoteCode, execute);
		}
	}

	/**
	 * 购物车计算
	 * @param money
	 * @return
	 */
	@Override
	public Map<String, Object> toShopping(String money) {
		// 购物车请求信息
		Map<String, Object> map = new HashMap<>();
		double v = Double.parseDouble(money);
		List<Object> pn = new ArrayList<>();
		if (this.promoteExecuteMap != null) {
			// 证明有优惠券
			for (Map.Entry<String, PromoteExecute> codes : this.promoteExecuteMap.entrySet()) {
				RuleResult ruleResult = DrlExecute.rulePromote(codes.getValue(), v);
				v = ruleResult.getFinallyMoney();
				pn.add(ruleResult);
			}
		}
		map.put("moneySumYuanJia", money);
		map.put("youhuiquanjiegou", pn);
		return map;
	}

	/**
	 * 组合业务规则Join方法
	 * @param name
	 * @param money
	 * @return
	 */
	private String ruleWorkMap(String name, double money) {
		Map<String, Object> map = new HashMap<>();
		// 组合Rule部分
		Map<String, Object> rule = new HashMap<>();
		map.put("rule", rule);
		rule.put("name", name);
		// 组合规则When部分
		Map<String, Object> when = new HashMap<>();
		map.put("condition", when);
		// 组合规则Then部分
		Map<String, Object> then = new HashMap<>();
		map.put("action", then);
		then.put("money", money);
		// 组合规则When And Then部分
		return JSONObject.toJSONString(map);
	}

}
