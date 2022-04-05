package com.smxknife.cloud.netflix.repository;

import com.smxknife.cloud.netflix.entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/29
 */
@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {

	List<OrderEvent> findByStatus(short status);
}
