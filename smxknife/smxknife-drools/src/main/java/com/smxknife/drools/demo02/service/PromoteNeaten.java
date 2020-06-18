package com.smxknife.drools.demo02.service;

import com.smxknife.drools.demo02.model.PromoteExecute;
import org.springframework.stereotype.Service;

/**
 * 规则库生成
 * @author smxknife
 * 2020/6/15
 */
@Service
public class PromoteNeaten {

	public PromoteExecute editRule(String rule) throws RuntimeException {
		PromoteExecute promoteExecute = new PromoteExecute();
		promoteExecute.setWorkContent(rule); // 促销业务规则
		// 规则库 初始化
		promoteExecute.getWorkSession();
		return promoteExecute;
	}
}
