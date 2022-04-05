package com.smxknife.flink.ceo.demo.ordertimeout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 订单事件
 * @author smxknife
 * 2020/8/31
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
	private String orderId;
	private String flag;
	private String tradeId;
	private long timestamp;
}
