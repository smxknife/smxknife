package com.smxknife.shardingsphere.jdbc.repository;

import com.smxknife.shardingsphere.jdbc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
