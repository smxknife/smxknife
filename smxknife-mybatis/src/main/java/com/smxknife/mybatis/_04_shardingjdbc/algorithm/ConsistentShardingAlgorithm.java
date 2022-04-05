package com.smxknife.mybatis._04_shardingjdbc.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * 一致性hash
 * @author smxknife
 * 2021/6/28
 */
public class ConsistentShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

	private ConsistentHashAlgorithm hashAlgorithm;

	public ConsistentShardingAlgorithm(ConsistentHashAlgorithm hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> preciseShardingValue) {

		if (availableTargetNames.isEmpty()) {
			return preciseShardingValue.getLogicTableName();
		}

		return hashAlgorithm.getTableNode(preciseShardingValue.getValue().toString());
	}

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> rangeShardingValue) {

		if (availableTargetNames.isEmpty()) {
			return availableTargetNames;
		}

		final Range<Long> valueRange = rangeShardingValue.getValueRange();



		return null;
	}
}
