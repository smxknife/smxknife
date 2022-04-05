package com.smxknife.springdatajpa.repository;

import com.smxknife.springdatajpa.entity.AccountElement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author smxknife
 * 2020/11/16
 */
public interface AccountElementRepository extends JpaRepository<AccountElement, Long> {
}
