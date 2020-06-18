package com.smxknife.drools.demo02.service;

import com.smxknife.drools.demo02.model.PromoteExecute;
import com.smxknife.drools.demo02.model.RuleResult;
import lombok.extern.slf4j.Slf4j;
import org.kie.internal.command.CommandFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/15
 */
@Slf4j
public class DrlExecute {
	private static DecimalFormat df = new DecimalFormat("######0.00");

	/**
	 * 判断购物车中所有参加活动的商品
	 * @param promoteExecute
	 * @param moneySum
	 * @return
	 */
	public static RuleResult rulePromote(PromoteExecute promoteExecute, Double moneySum) {
		// 判断业务规则是否存在
		RuleResult ruleResult = new RuleResult();
		ruleResult.setMoneySum(moneySum);
		log.info("优惠前的价格：{}", moneySum);
		// 统计完成后再将参数insert促销规则中
		List cmdCondition = new ArrayList<>();
		cmdCondition.add(CommandFactory.newInsert(ruleResult));
		promoteExecute.getWorkSession().execute(CommandFactory.newBatchExecution(cmdCondition));
		log.info("优惠后的价格：{}", ruleResult.getFinallyMoney());
		return ruleResult;
	}
}
