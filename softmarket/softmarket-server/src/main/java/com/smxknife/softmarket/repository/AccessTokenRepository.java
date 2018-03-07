package com.smxknife.softmarket.repository;

import com.smxknife.softmarket.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
}
