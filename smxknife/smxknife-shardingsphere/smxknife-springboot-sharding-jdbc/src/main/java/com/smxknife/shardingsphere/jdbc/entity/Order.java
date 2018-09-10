package com.smxknife.shardingsphere.jdbc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {


		private static final long serialVersionUID = 661434701950670670L;

		@Id
		@Column(name = "order_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long orderId;

		@Column(name = "user_id")
		private int userId;

		@Column(name = "status")
		private String status;

		public long getOrderId() {
			return orderId;
		}

		public void setOrderId(final long orderId) {
			this.orderId = orderId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(final int userId) {
			this.userId = userId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(final String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return String.format("order_id: %s, user_id: %s, status: %s", orderId, userId, status);
		}
}
