package com.smxknife.drools.demo02.dao;

import com.smxknife.drools.demo02.model.PromoteExecute;
import org.springframework.stereotype.Repository;

/**
 * @author smxknife
 * 2020/6/15
 */
@Repository
public interface PromoteExecuteMapper {
	int insertPromoteExecute(PromoteExecute promote);
}
