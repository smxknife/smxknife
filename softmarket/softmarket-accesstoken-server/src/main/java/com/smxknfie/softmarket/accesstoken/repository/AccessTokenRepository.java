package com.smxknfie.softmarket.accesstoken.repository;

import com.smxknfie.softmarket.accesstoken.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
}
