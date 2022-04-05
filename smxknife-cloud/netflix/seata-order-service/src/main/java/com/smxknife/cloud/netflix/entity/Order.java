package com.smxknife.cloud.netflix.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author smxknife
 * 2021/5/31
 */
@Entity
@Table(name = "tb_orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userId;

	private String commodityCode;

	private int count;

	private long money;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private Date updateTime;
}
