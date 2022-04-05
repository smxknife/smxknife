package com.smxknife.flink.table.demo.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smxknife
 * 2020/9/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyData {
	private String dataCode;
	private String usage;
	private String energyClassCode;
	private String energyTypeCode;
	private Double value;
	private Long timestamp;
}
