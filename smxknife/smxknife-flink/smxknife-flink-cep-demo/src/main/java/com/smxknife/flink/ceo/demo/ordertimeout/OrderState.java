package com.smxknife.flink.ceo.demo.ordertimeout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author smxknife
 * 2020/8/31
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderState {
	private String orderId;
	private Long createTime;
	private Long payTime;
	private String message;
}
