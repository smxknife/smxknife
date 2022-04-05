package com.smxknife.cloud.netflix.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/5/29
 */
@Entity
@Table(name = "tb_order_events", indexes = {
		@Index(name = "idx_order_id", columnList = "orderId", unique = true)
})
@Data
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String orderId;

	private Short status;

	private String name;

	@CreationTimestamp
	private LocalDateTime createTime;

	@UpdateTimestamp
	private LocalDateTime updateTime;

	public enum Status {
		NEW(0), RECEIVED(1), ORDERED(2)
		;

		int status;

		Status(int status) {
			this.status = status;
		}

		public short getStatus() {
			return (short) status;
		}
	}
}
