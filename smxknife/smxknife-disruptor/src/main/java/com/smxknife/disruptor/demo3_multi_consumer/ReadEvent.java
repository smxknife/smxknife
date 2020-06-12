package com.smxknife.disruptor.demo3_multi_consumer;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/3/5
 */
@Data
public class ReadEvent {
	private LocalDateTime localDateTime;
	private Double value;
}
