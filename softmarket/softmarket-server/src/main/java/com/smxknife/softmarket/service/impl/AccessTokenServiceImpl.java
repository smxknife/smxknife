package com.smxknife.softmarket.service.impl;

import com.smxknife.softmarket.domain.AccessToken;
import com.smxknife.softmarket.repository.AccessTokenRepository;
import com.smxknife.softmarket.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Override
    public AccessToken accessToken() {
        return accessTokenRepository.findOne(1L);
    }

    @Override
    public String flushAccessToken(String accessToken) {
        AccessToken token = new AccessToken();
        token.setAccessToken(accessToken);
        return accessTokenRepository.saveAndFlush(token).getAccessToken();
    }
}
