package com.smxknfie.softmarket.accesstoken.service.impl;

import com.smxknfie.softmarket.accesstoken.domain.AccessToken;
import com.smxknfie.softmarket.accesstoken.repository.AccessTokenRepository;
import com.smxknfie.softmarket.accesstoken.service.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Override
    @Cacheable("accessToken")
    public String accessToken() {
	    AccessToken token = accessTokenRepository.findOne(1L);
	    if (token == null) return "";
	    return token.getAccessToken();
    }

    @Override
    @CachePut("accessToken")
    public String flushAccessToken(String accessToken) {
        logger.info("flushAccessToken(String)");
        AccessToken token = new AccessToken();
        token.setAccessToken(accessToken);
        return accessTokenRepository.saveAndFlush(token).getAccessToken();
    }

    @Override
    @CachePut("accessToken")
    public String flushAccessToken(AccessToken accessToken) {
        logger.info("flushAccessToken(AccessToken)");
        return accessTokenRepository.saveAndFlush(accessToken).getAccessToken();
    }
}
