package com.smxknife.springboot.v2.jpa.transaction.repository;

import com.smxknife.springboot.v2.jpa.transaction.domain.Lion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author smxknife
 * 2019-08-09
 */
public interface LionRepository extends JpaRepository<Lion, Long> {
}
