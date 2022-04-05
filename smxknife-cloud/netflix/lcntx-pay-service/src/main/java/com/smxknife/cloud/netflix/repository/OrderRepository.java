package com.smxknife.cloud.netflix.repository;

import com.smxknife.cloud.netflix.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/29
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByStatus(short status);
}
