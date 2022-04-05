package com.smxknife.cloud.netflix.repository;

import com.smxknife.cloud.netflix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author smxknife
 * 2021/5/31
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUserId(String userId);
}
