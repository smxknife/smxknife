package com.smxknife.shardingsphere.jdbc.repository;

import com.smxknife.shardingsphere.jdbc.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
