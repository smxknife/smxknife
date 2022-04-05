package com.smxknife.cloud.netflix.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author smxknife
 * 2021/5/31
 */
@LocalTCC
public interface TccBizService {

	@TwoPhaseBusinessAction(name = "purchaseTccAction", commitMethod = "purchaseConfirm", rollbackMethod = "purchaseCancel")
	void purchase(@BusinessActionContextParameter String userId, @BusinessActionContextParameter String commodityCode, @BusinessActionContextParameter int orderCount, BusinessActionContext context);

	void purchaseConfirm(BusinessActionContext context);

	void purchaseCancel(BusinessActionContext context);
}
