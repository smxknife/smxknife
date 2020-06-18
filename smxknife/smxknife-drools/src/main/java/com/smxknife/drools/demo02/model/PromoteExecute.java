package com.smxknife.drools.demo02.model;

import org.kie.api.KieBase;
import org.kie.api.runtime.StatelessKieSession;

import java.util.List;
import java.util.Objects;

import static com.smxknife.drools.demo02.service.NewKieBase.ruleKieBase;

/**
 * @author smxknife
 * 2020/6/15
 */
public class PromoteExecute {

	// 促销编号
	private String promoteCode;
	// 业务Kbase
	private KieBase workKbase;
	// 业务Session
	private StatelessKieSession workSession;
	// 规则内容
	private String workContent;
	// 促销规则名称
	private List<String> ruleName;

	private String promoteName;

	public String getPromoteCode() {
		return promoteCode;
	}

	public void setPromoteCode(String promoteCode) {
		this.promoteCode = promoteCode;
	}

	public KieBase getWorkKbase() {
		if (Objects.isNull(this.workKbase)) {
			this.setWorkKbase();
		}
		return workKbase;
	}

	public void setWorkKbase() {
		this.workKbase = ruleKieBase(this.getWorkContent());
	}

	public StatelessKieSession getWorkSession() {
		if (Objects.isNull(this.workSession)) {
			this.setWorkSession();
		}
		return workSession;
	}

	public void setWorkSession() {
		if (Objects.nonNull(getWorkKbase())) {
			this.workSession = this.getWorkKbase().newStatelessKieSession();
		}
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public List<String> getRuleName() {
		return ruleName;
	}

	public void setRuleName(List<String> ruleName) {
		this.ruleName = ruleName;
	}

	public String getPromoteName() {
		return promoteName;
	}

	public void setPromoteName(String promoteName) {
		this.promoteName = promoteName;
	}
}
